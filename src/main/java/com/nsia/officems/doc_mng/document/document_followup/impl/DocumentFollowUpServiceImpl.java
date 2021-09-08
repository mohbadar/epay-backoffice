package com.nsia.officems.doc_mng.document.document_followup.impl;

import com.nsia.officems._admin.department.Department;
import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.doc_mng.document.Document;
import com.nsia.officems.doc_mng.document_followup_status.DocumentFollowUpStatus;
import com.nsia.officems.doc_mng.document_followup_type.DocumentFollowUpType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.FileUploadUtil;
import com.nsia.officems.doc_mng.document.DocumentService;
import com.nsia.officems.doc_mng.document.document_followup.DocumentFollowUp;
import com.nsia.officems.doc_mng.document.document_followup.DocumentFollowUpRepository;
import com.nsia.officems.doc_mng.document.document_followup.DocumentFollowUpService;
import com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.DocActivityFollowUpService;
import com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.DocFollowUpActivity;
import com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.DocFollowUpActivityRepository;
import com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.dto.DocFollowUpActivityDTO;
import com.nsia.officems.doc_mng.document.document_followup.dto.DocumentFollowUpDto;
import com.nsia.officems.doc_mng.document.document_followup.dto.DocumentFollowUpMapper;
import com.nsia.officems.doc_mng.document_followup_status.DocumentFollowUpStatusService;
import com.nsia.officems.doc_mng.document_followup_type.DocumentFollowUpTypeService;
import com.nsia.officems.doc_mng.document_status.DocumentStatusService;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentFollowUpServiceImpl implements DocumentFollowUpService {
    @Value("${app.upload.doc_mng.documents}")
    private String uploadDir;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Autowired
    private DocumentFollowUpRepository repo;

    @Autowired
    private DocumentFollowUpTypeService typeService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private DocumentFollowUpStatusService documentFollowUpStatusService;

    @Autowired
    private DocumentStatusService documentStatusService;

    @Autowired
    private DocFollowUpActivityRepository docFollowUpActivityRepository;

    @Autowired
    private DocActivityFollowUpService docActivityFollowUpService;

    @Autowired
    private DateTimeChange dateTimeChange;


    @Override
    public List<DocumentFollowUp> findAll() {
        //return repo.findAll();
        return repo.findAllWithDeletedNull();
    }

    public DocumentFollowUp save(DocumentFollowUp documentFollowUp) {
        if (documentFollowUp != null) {
            return repo.save(documentFollowUp);
        }

        return null;
    }

    @Override
    public List<DocumentFollowUpDto> findByDocument_id(Long id) {
        List<DocumentFollowUpDto> documentFollowUpDtos = new ArrayList<>();
        String lang = customUserService.getCurrentLang();
        User loggedInUser = userService.getLoggedInUser();
        Department department = loggedInUser.getDepartment();
        List<DocumentFollowUp> documentFollowUps = repo.findByDocAndDepartmentAndAssignedUser(id, department.getId(), loggedInUser.getId());
//        List<DocumentFollowUp> documentFollowUps = repo.findByDocumentIdAndDepartmentId(id, department.getId());

        for (DocumentFollowUp documentFollowUp : documentFollowUps) {

            if(userService.hasAuthority(new SimpleGrantedAuthority("DOCMNG_FOLLOWUP_ASSIGN"))) {
                DocumentFollowUpDto dto = prepareDocFollowUpDTO(documentFollowUp);
                documentFollowUpDtos.add(dto);
            } else {
                if(documentFollowUp.getCreatedBy().equals(loggedInUser) || documentFollowUp.getAssignedTo().equals(loggedInUser)) {
                    DocumentFollowUpDto dto = prepareDocFollowUpDTO(documentFollowUp);
                    documentFollowUpDtos.add(dto);
                }
            }
        }
        return documentFollowUpDtos;
    }

    private DocumentFollowUpDto prepareDocFollowUpDTO(DocumentFollowUp documentFollowUp) {
        String lang = customUserService.getCurrentLang();
        List<DocFollowUpActivityDTO> docFollowUpActivityDTOS = new ArrayList<>();

        List<DocFollowUpActivity> mDocActivities = docFollowUpActivityRepository.findByDocumentFollowUp_idOrderByCreatedAt(documentFollowUp.getId());

        for (DocFollowUpActivity activity : mDocActivities) {
            if((activity.getIsFinal() != null && activity.getIsFinal()) || activity.getCreatedBy().equals(userService.getLoggedInUser())) {
                DocFollowUpActivityDTO activityDTO = DocumentFollowUpMapper.mapDocFollowupActivityDTO(activity, lang);
                docFollowUpActivityDTOS.add(activityDTO);
            }

        }

        DocFollowUpActivity docFollowUpActivity = docActivityFollowUpService.findTopByIsFinalAndDocumentFollowUp_idOrderByCreatedAtDesc(true, documentFollowUp.getId());
        return DocumentFollowUpMapper.mapDTO(documentFollowUp, docFollowUpActivityDTOS, docFollowUpActivity, lang);
    }

    public List getFollowUpCountByType(Long id) {
        return repo.getFollowUpCountByType(id);
    }


    @Override
    public DocumentFollowUp findById(Long id) {
        Optional<DocumentFollowUp> optionalObj = repo.findById(id);
        return optionalObj.orElse(null);
    }

    @Override
    public DocumentFollowUpDto getDetails(Long id) {
        String lang = customUserService.getCurrentLang();
        DocumentFollowUp documentFollowUp = findById(id);
        DocumentFollowUpDto dto = DocumentFollowUpMapper.mapDTO(documentFollowUp, null, null, lang);
        return dto;
    }

    /**
     * Assign multiple users at once
     *
     * @param dueDateString: due date for the users
     * @param userIds        the IDs of the users to be assigned.
     * @param docNo          the ID of the document for which the activities are done
     * @return List of the users assigned
     */
    @Override
    public List<DocumentFollowUp> assignUsers(String dueDateString, List<Long> userIds, Long docNo) {
        Date dueDate = null;
        if (docNo != null) {
            Document document = documentService.findById(docNo);
            List<User> users = userService.findByIdIn(userIds);
            DocumentFollowUpStatus documentFollowUpStatus = documentFollowUpStatusService.findById(1L);
            List<DocumentFollowUp> saveDocFollowUps = new ArrayList<>();
            if (dueDateString != null) {
                dueDate = dateTimeChange.convertPersianDateToGregorianDate(dueDateString);
            }

            User createdBy = userService.getLoggedInUser();
            for (User user : users) {
                DocumentFollowUp documentFollowUp = new DocumentFollowUp();
                documentFollowUp = DocumentFollowUpMapper.map(documentFollowUp, user, dueDate, document, documentFollowUpStatus, createdBy);
                documentFollowUp = save(documentFollowUp);
                saveDocFollowUps.add(documentFollowUp);
            }

            if(document.getDocumentStatus().getId().equals(1L)) {
                document.setDocumentStatus(documentStatusService.findById(2L));
                documentService.save(document);
            }

            return saveDocFollowUps;
        }

        return null;
    }

    @Override
    public DocumentFollowUp assignUser(String dueDateString, Long userId, Long docNo) {
        Date dueDate = null;
        if (docNo != null) {
            Document document = documentService.findById(docNo);
            User user = userService.findById(userId);
            DocumentFollowUpStatus documentFollowUpStatus = documentFollowUpStatusService.findById(1L);
            if (dueDateString != null && !dueDateString.isBlank() && !dueDateString.isEmpty()) {
                dueDate = dateTimeChange.convertPersianDateToGregorianDate(dueDateString);
            }

            User createdBy = userService.getLoggedInUser();
            DocumentFollowUp documentFollowUp = new DocumentFollowUp();
            documentFollowUp = DocumentFollowUpMapper.map(documentFollowUp, user, dueDate, document, documentFollowUpStatus, createdBy);
            documentFollowUp = save(documentFollowUp);

            if(document.getDocumentStatus().getId().equals(1L)) {
                document.setDocumentStatus(documentStatusService.findById(2L));
                documentService.save(document);
            }

            return documentFollowUp;
        }

        return null;
    }

    @Override
    public DocFollowUpActivity create(DocFollowUpActivityDTO dto, MultipartFile file) {
        DocFollowUpActivity docFollowUpActivity = new DocFollowUpActivity();
        DocumentFollowUp documentFollowUp = findById(dto.getDocFollowUpId());
        DocumentFollowUpStatus documentFollowUpStatus = documentFollowUpStatusService.findById(dto.getDocumentFollowupStatusNo());
        DocumentFollowUpType documentFollowUpType = typeService.findById(dto.getDocumentFollowupTypeNo());
        DocFollowUpActivity docActivity = DocumentFollowUpMapper.mapDocFollowUpActivity(docFollowUpActivity, dto, documentFollowUp, documentFollowUpStatus, documentFollowUpType);

        docActivity.setCreatedBy(this.userService.getLoggedInUser());

        docActivity = docFollowUpActivityRepository.save(docActivity);

        if (file != null) {
            String fileName = fileUploadUtil.saveAttachment(file, uploadDir, docActivity.getId().toString(), "followup");
            docActivity.setFileName(fileName);

            return docFollowUpActivityRepository.save(docActivity);
        }
        return docActivity;
    }

    @Override
    public Boolean update(Long id, DocumentFollowUpDto dto, MultipartFile file) {
//        Optional<DocumentFollowUp> documentFollowUp = repo.findById(id);
//        if (documentFollowUp.isPresent()) {
//            DocumentFollowUp follow = DocumentFollowUpMapper.MapProposalDto(documentFollowUp.get(), dto, documentService, typeService, documentFollowUpStatusService, documentStatusService, userService);
//            if (follow != null) {
//                if (file != null) {
//                    String fileName = fileUploadUtil.saveAttachment(file, uploadDir, follow.getId().toString(), "followup");
////                    follow.setFileName(fileName);
//                }
//                // follow.setUpdatedBy(this.userService.getLoggedInUser());
//                repo.save(follow);
//                return true;
//            }
//        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<DocumentFollowUp> pOptional = repo.findById(id);

        if (pOptional.isPresent()) {
            DocumentFollowUp proposal = pOptional.get();
            proposal.setDeleted(true);
            proposal.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            repo.save(proposal);
            return true;
        }

        return false;
    }

    @Override
    public File downloadAttachment(Long id) throws Exception {
        Optional<DocumentFollowUp> documentUpload = repo.findById(id);
        if (documentUpload.isPresent()) {
//            String fileName = documentUpload.get().getFileName();
            String fileName = "tests";
            String saveDirectory = uploadDir + "/" + id + "/" + fileName;
            if (new File(saveDirectory).exists()) {
                return new File(saveDirectory);
            }
        }

        return null;
    }

    @Override
    public Map<String, Object> updateDueDate(Long id, String dueDateString) {
        Map<String, Object> m = new HashMap<>();
        if(id != null) {
            DocumentFollowUp documentFollowUp = findById(id);

            Date dueDate = dateTimeChange.convertPersianDateToGregorianDate(dueDateString);
            documentFollowUp.setDueDate(Instant.ofEpochMilli(dueDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime());
            documentFollowUp = save(documentFollowUp);

            m.put("id", documentFollowUp.getId());
            m.put("dueDate", documentFollowUp.getDueDate() != null ? documentFollowUp.getDueDate().toString(): null);

            return m;
        }

        return null;
    }


}