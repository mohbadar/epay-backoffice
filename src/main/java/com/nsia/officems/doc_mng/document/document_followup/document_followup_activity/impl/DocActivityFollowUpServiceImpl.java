package com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.impl;

import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.FileUploadUtil;
import com.nsia.officems.doc_mng.document.document_followup.DocumentFollowUp;
import com.nsia.officems.doc_mng.document.document_followup.DocumentFollowUpService;
import com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.DocActivityFollowUpService;
import com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.DocFollowUpActivity;
import com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.DocFollowUpActivityRepository;
import com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.dto.DocFollowUpActivityDTO;
import com.nsia.officems.doc_mng.document.document_followup.dto.DocumentFollowUpMapper;
import com.nsia.officems.doc_mng.document_followup_status.DocumentFollowUpStatus;
import com.nsia.officems.doc_mng.document_followup_status.DocumentFollowUpStatusService;
import com.nsia.officems.doc_mng.document_followup_type.DocumentFollowUpType;
import com.nsia.officems.doc_mng.document_followup_type.DocumentFollowUpTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class DocActivityFollowUpServiceImpl implements DocActivityFollowUpService {

    @Value("${app.upload.doc_mng.documents}")
    private String uploadDir;

    @Autowired
    DocFollowUpActivityRepository docFollowUpActivityRepository;

    @Autowired
    UserService userService;

    @Autowired
    DocumentFollowUpService documentFollowUpService;

    @Autowired
    DocumentFollowUpStatusService documentFollowUpStatusService;

    @Autowired
    DocumentFollowUpTypeService typeService;

    @Autowired
    FileUploadUtil fileUploadUtil;

    @Autowired
    CustomUserService customUserService;

    @Override
    public DocFollowUpActivity findTopByIsFinalAndDocumentFollowUp_idOrderByCreatedAtDesc(Boolean isFinal, Long docFollowUpId) {
        return docFollowUpActivityRepository.findTopByIsFinalAndDocumentFollowUp_idOrderByCreatedAtDesc(isFinal, docFollowUpId);
    }

    public DocFollowUpActivity findById(Long id) {
        return docFollowUpActivityRepository.findById(id).orElse(null);
    }

    public DocFollowUpActivity save(DocFollowUpActivity docFollowUpActivity) {
        return docFollowUpActivityRepository.save(docFollowUpActivity);
    }

    @Override
    public DocFollowUpActivityDTO getDetails(Long id) {
        if(id != null) {
            DocFollowUpActivity docFollowUpActivity = findById(id);
            return  DocumentFollowUpMapper.mapDocFollowupActivityDTO(docFollowUpActivity, customUserService.getCurrentLang());
        }
        return null;
    }

    @Override
    public Boolean deleteDocFollowUpActivity(Long docActivityNo) {
        if(docActivityNo != null) {
            DocFollowUpActivity docFollowUpActivity = findById(docActivityNo);
            if(docFollowUpActivity != null) {
                docFollowUpActivity.setDeleted(true);
                docFollowUpActivity.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                docFollowUpActivity.setDeletedBy(userService.getLoggedInUser().getId());

                save(docFollowUpActivity);
                return true;
            }
        }
        return false;
    }

    @Override
    public DocFollowUpActivity create(DocFollowUpActivityDTO dto, MultipartFile file) {
        DocFollowUpActivity docFollowUpActivity = new DocFollowUpActivity();
        DocumentFollowUp documentFollowUp =  documentFollowUpService.findById(dto.getDocFollowUpId());
        DocumentFollowUpStatus documentFollowUpStatus = documentFollowUpStatusService.findById(dto.getDocumentFollowupStatusNo());
        DocumentFollowUpType documentFollowUpType = typeService.findById(dto.getDocumentFollowupTypeNo());
        DocFollowUpActivity docActivity = DocumentFollowUpMapper.mapDocFollowUpActivity(docFollowUpActivity, dto, documentFollowUp, documentFollowUpStatus, documentFollowUpType);

        docActivity.setCreatedBy(this.userService.getLoggedInUser());

        docActivity = docFollowUpActivityRepository.save(docActivity);

        if (file != null) {
            String  documentId = documentFollowUp.getDocument().getId().toString();
            String followupId = documentFollowUp.getId().toString();
            String mUploadDir = uploadDir + File.separator + documentId + File.separator + "followup" + File.separator + followupId;

            String fileName = fileUploadUtil.saveAttachment(file, mUploadDir, docActivity.getId().toString(), "activity");
            docActivity.setFileName(fileName);

            return docFollowUpActivityRepository.save(docActivity);
        }
        return docActivity;
    }

    @Override
    public DocFollowUpActivityDTO update(DocFollowUpActivityDTO dto, Long id, MultipartFile file) {
        DocFollowUpActivityDTO docFollowUpActivityDTO = new DocFollowUpActivityDTO();
        DocFollowUpActivity docFollowUpActivity = findById(id);
        if(docFollowUpActivity != null) {
            DocumentFollowUpStatus documentFollowUpStatus = documentFollowUpStatusService.findById(dto.getDocumentFollowupStatusNo());
            DocumentFollowUpType documentFollowUpType = typeService.findById(dto.getDocumentFollowupTypeNo());
            docFollowUpActivity = DocumentFollowUpMapper.mapDocFollowUpActivity(docFollowUpActivity, dto, docFollowUpActivity.getDocumentFollowUp(), documentFollowUpStatus, documentFollowUpType);

            if (file != null) {
                String  documentId = docFollowUpActivity.getDocumentFollowUp().getDocument().getId().toString();
                String followupId = docFollowUpActivity.getDocumentFollowUp().getId().toString();
                 String mUploadDir = uploadDir + File.separator + documentId + File.separator + "followup" + File.separator + followupId;

                String fileName = fileUploadUtil.saveAttachment(file, mUploadDir, docFollowUpActivity.getId().toString(), "activity");
                docFollowUpActivity.setFileName(fileName);
            }

            docFollowUpActivity = docFollowUpActivityRepository.save(docFollowUpActivity);
            docFollowUpActivityDTO = DocumentFollowUpMapper.mapDocFollowupActivityDTO(docFollowUpActivity, customUserService.getCurrentLang());
            return docFollowUpActivityDTO;
        }
        return null;
    }

    @Override
    public File downloadAttachment(Long id) throws Exception {
        Optional<DocFollowUpActivity> optionalDocFollowUpActivity = docFollowUpActivityRepository.findById(id);
        if (optionalDocFollowUpActivity.isPresent()) {
            DocFollowUpActivity docFollowUpActivity = optionalDocFollowUpActivity.get();
            String  documentId = docFollowUpActivity.getDocumentFollowUp().getDocument().getId().toString();
            String followupId = docFollowUpActivity.getDocumentFollowUp().getId().toString();
            String mUploadDir = uploadDir + File.separator + documentId + File.separator + "followup" + File.separator + followupId;

            String fileName = docFollowUpActivity.getFileName();
            String saveDirectory = mUploadDir + "/" + id + "/" + fileName;
            System.out.println("---------------------------------- " + saveDirectory);
            if (new File(saveDirectory).exists()) {
                return new File(saveDirectory);
            }
        }
        return null;
    }

    @Override
    public DocFollowUpActivityDTO finalizeActivity(Long id) {
        if(id != null) {
            DocFollowUpActivity docFollowUpActivity = findById(id);
            docFollowUpActivity.setIsFinal(true);
            docFollowUpActivity = save(docFollowUpActivity);
            DocFollowUpActivityDTO dto = DocumentFollowUpMapper.mapDocFollowupActivityDTO(docFollowUpActivity, customUserService.getCurrentLang());
            return dto;
        }
        return null;
    }
}
