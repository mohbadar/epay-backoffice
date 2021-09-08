package com.nsia.officems.doc_mng.document.impl;

import com.nsia.officems._admin.department.Department;
import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._admin.district.DistrictService;
import com.nsia.officems._admin.document_category.DocumentCategoryService;
import com.nsia.officems._admin.issuing_authority.IssuingAuthorityService;
import com.nsia.officems._admin.province.ProvinceService;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.doc_mng.document.Document;
import com.nsia.officems.doc_mng.document.DocumentRepository;
import com.nsia.officems.doc_mng.document.DocumentService;
import com.nsia.officems.doc_mng.document.document_note_section.DocumentNoteSection;
import com.nsia.officems.doc_mng.document.document_note_section.DocumentNoteSectionService;
import com.nsia.officems.doc_mng.document.document_review.DocumentReview;
import com.nsia.officems.doc_mng.document.document_review.DocumentReviewService;
import com.nsia.officems.doc_mng.document.document_receive.DocumentReceiveService;
import com.nsia.officems.doc_mng.document.document_receive.dto.DocumentReceiveDto;
import com.nsia.officems.doc_mng.document.dto.DocumentDto;
import com.nsia.officems.doc_mng.document.dto.DocumentEvaluationDto;
import com.nsia.officems.doc_mng.document.dto.DocumentMapper;
import com.nsia.officems.doc_mng.document.proj.DocumentAbstractViewProj;
import com.nsia.officems.doc_mng.document_note_type.DocumentNoteTypeService;
import com.nsia.officems.doc_mng.document_status.DocumentStatusService;
import com.nsia.officems.doc_mng.document_status.DocumentStatus;
import com.nsia.officems.doc_mng.document.document_receive.DocumentReceive;

import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._util.DataTablesUtil;

import com.nsia.officems.doc_mng.document_type.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Value("${app.upload.doc_mng.document}")
    private String uploadDir;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentTypeService documentTypeService;

    @Autowired
    private DocumentStatusService documentStatusService;

    @Autowired
    private DocumentReviewService documentReviewService;

    @Autowired
    private DocumentReceiveService documentReceiveService;

    @Autowired
    private DocumentNoteSectionService documentNoteSectionService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private DocumentCategoryService documentCategoryService;

    @Autowired
    private IssuingAuthorityService issuingAuthorityService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private DocumentNoteTypeService noteTypeService;

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        User currentLoginUser = userService.getLoggedInUser();
        List<Long> accessableDepartmentIds = userService.getAccessableDepartmentIds(currentLoginUser);
        String depIds = StringUtils.join(accessableDepartmentIds, ',');
        String joinClause = " inner join public.department fdep on fdep.id=doc.from_department_id ";
        joinClause += " left join public.department tdep on tdep.id=doc.to_department_id ";
        joinClause += " inner join public.doc_mng_document_type doc_type on doc_type.id=doc.document_type_id ";
        joinClause += " inner join public.doc_mng_document_status doc_status on doc_status.id=doc.document_status_id ";
        joinClause += " inner join public.user_tbl usr on usr.id=doc.created_by ";
        String whereClause = " doc.status = 'FINAL' and (from_department_id in(" + depIds + ") or to_department_id in("
                + depIds + ")) ";

        String filter = dataTablesUtil.whereClause(filters,"doc");
        if(filter.length() > 0){
            whereClause+=" AND ";
        }
        whereClause +=  filter;
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.doc_mng_document doc", null, joinClause, whereClause, groupByClause,
                input);
    }

    public Object getFollowupList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " inner join public.department fdep on fdep.id=doc.from_department_id ";
        joinClause += " left join public.department tdep on tdep.id=doc.to_department_id ";
        joinClause += " inner join public.doc_mng_document_type doc_type on doc_type.id=doc.document_type_id ";
        joinClause += " inner join public.doc_mng_document_status doc_status on doc_status.id=doc.document_status_id ";
        joinClause += " inner join public.user_tbl usr on usr.id=doc.created_by ";
        String whereClause = " doc.followup is true " + dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.doc_mng_document doc", null, joinClause, whereClause, groupByClause,
                input);
    }

    @Override
    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    @Override
    public Document findById(Long id) {
        Optional<Document> optionalObj = documentRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public List<DocumentAbstractViewProj> findByMainDocumentId(Long id) {
        List<DocumentAbstractViewProj> viewProjection = new ArrayList<>();
        List<Document> documents = documentRepository.findByMainDocumentIdOrderByCreatedAtDesc(id);

        for (Document document : documents) {
            DocumentAbstractViewProj viewProj = new DocumentAbstractViewProj();
            viewProj.setId(document.getId());
            viewProj.setType(document.getType());
            viewProj.setDoumentTypeName(document.getDocumentType() == null? null: document.getDocumentType().getNameDr());
            viewProj.setStatus(document.getStatus());
            viewProj.setDocumentStatusName(document.getDocumentStatus() == null? null: document.getDocumentStatus().getNameDr());
            viewProj.setFromDepartmentName(document.getFromDepartment() == null? null: document.getFromDepartment().getNameDr());
            viewProj.setDocumentStatusName(document.getDocumentStatus() == null? null: document.getDocumentStatus().getNameDr());
            viewProj.setDocumentTypeId(document.getDocumentType() == null? null: document.getDocumentType().getId());
            viewProj.setCreatedBy(document.getCreatedBy() == null? null: document.getCreatedBy());
            viewProj.setCreatedAtDate(document.getCreatedAt() == null? null: document.getCreatedAt().toString());
            viewProj.setFromEntityName(document.getFromEntity() == null? null: document.getFromEntity().getNameDr());

            viewProjection.add(viewProj);
        }

         return viewProjection;
    }

    @Override
    public List<Document> findbyIdIn(List<Long> ids) {
        return documentRepository.findByIdIn(ids);
    }

    @Override
    public Document save(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public Document create(DocumentDto dto) {
        Document newDocument = new Document();
        User currentLoginUser = userService.getLoggedInUser();
        newDocument = DocumentMapper.map(dto, newDocument, this, departmentService, documentTypeService, userService,
                issuingAuthorityService,documentCategoryService,provinceService,districtService, noteTypeService);
        List<DocumentReview> documentReviews = newDocument.getReviews();
        List<DocumentNoteSection> documentNoteSections = newDocument.getNoteSections();
        newDocument.setReviews(null);
        newDocument.setNoteSections(null);
        if (newDocument != null) {
            newDocument.setDocumentType(documentTypeService.findById(dto.getDocumentTypeId()));
            newDocument.setCreatedBy(currentLoginUser);
            newDocument.setCreatedBy(userService.getLoggedInUser());
            newDocument.setStatus("DRAFT");
            // نااجرا
            newDocument.setDocumentStatus(documentStatusService.findById(Long.valueOf(1)));
            newDocument = documentRepository.save(newDocument);

            if (documentReviews != null) {
                for (DocumentReview documentReview : documentReviews) {
                    documentReview.setDocument(newDocument);
                }
                documentReviews = documentReviewService.saveAll(documentReviews);
                newDocument.setReviews(documentReviews);
                newDocument = save(newDocument);
            }

            if (documentNoteSections != null) {
                for (DocumentNoteSection documentNoteSection : documentNoteSections) {
                    documentNoteSection.setDocument(newDocument);
                }
                documentNoteSections = documentNoteSectionService.saveAll(documentNoteSections);
                newDocument.setNoteSections(documentNoteSections);
                newDocument = save(newDocument);
            }

            return newDocument;
        }
        return null;
    }

    @Override
    public Document update(Long id, DocumentDto dto) {
        Document document = findById(id);
        if (document != null) {
            if (document.getStatus().equals("FINAL")) {
                throw new AccessDeniedException("You can not update the final document");
            }

            Document documentToBeUpdated = DocumentMapper.map(dto, document, this, departmentService,

                    documentTypeService, userService,issuingAuthorityService,documentCategoryService,provinceService,
                    districtService, noteTypeService);

            if (!documentToBeUpdated.equals(null)) {
                Document updatedDocument = documentRepository.save(documentToBeUpdated);
                return updatedDocument;
            }
        }
        return null;
    }

    @Override
    public Document setDocumentStatusExecuted(Long id) {
        Document document = findById(id);
        User currentLoginUser = userService.getLoggedInUser();
        if (document != null) {
            if (!document.getCreatedBy().getId().equals(currentLoginUser.getId())) {
                throw new AccessDeniedException("Only document owner can set status as Executed");
            }

            DocumentStatus documentStatus = document.getDocumentStatus();
            if (!documentStatus.getId().equals(2L)) {
                throw new AccessDeniedException(
                        "Only document owner can set status as Executed only after Under Execution status");
            }
            if (!documentStatus.getId().equals(3L)) {
                DocumentStatus newDocumentStatus = documentStatusService.findById(3L);
                document.setCompletionDate(new Date());
                document.setDocumentStatus(newDocumentStatus);
                return save(document);
            }
            return document;
        }
        return null;
    }

    @Override
    public Document finalizeDocumentStatus(Long id) {
        Document document = findById(id);
        User currentLoginUser = userService.getLoggedInUser();
        if (document != null) {
            if (!document.getCreatedBy().getId().equals(currentLoginUser.getId())) {
                throw new AccessDeniedException("Only document owner can finalize");
            }

            if (document.getStatus() == null || !document.getStatus().equals("FINAL")) {
                List<DocumentReview> documentReviews = document.getReviews();
                for (DocumentReview documentReview : documentReviews) {
                    if (documentReview.getDecision() == null || !documentReview.getDecision().equals("APPROVED")) {
                        throw new AccessDeniedException("You need to edit and get approval from all reviewers");
                    }
                }
                document.setStatus("FINAL");

                // DocType  8: ARCHIVE  10: REJECT
                if (document.getDocumentType().getSlug().equals("ARCHIVE")
                        || document.getDocumentType().getSlug().equals("REJECT")
                        || document.getDocumentType().getSlug().equals("HUKUM_PESHNIHAD")
                        || document.getDocumentType().getSlug().equals("ESTILAM_RESPONSE")) {
                    Document mainDocument = document.getMainDocument();
                    // 3: اجرا
                    mainDocument.setDocumentStatus(documentStatusService.findById(3L));
                    mainDocument = save(mainDocument);
                }

                List<DocumentReceive> receives = new ArrayList<>();
                if (document.getToDepartment() != null) {
                    DocumentReceive toReceive = new DocumentReceive();
                    toReceive.setDepartment(document.getToDepartment());
                    toReceive.setDecision("PENDING");
                    toReceive.setCreatedBy(currentLoginUser);
                    toReceive.setDocument(document);
                    receives.add(toReceive);
                }

                for (Department ccDepartment : document.getCcDepartments()) {
                    DocumentReceive ccReceive = new DocumentReceive();
                    ccReceive.setDepartment(ccDepartment);
                    ccReceive.setDecision("PENDING");
                    ccReceive.setCreatedBy(currentLoginUser);
                    ccReceive.setDocument(document);
                    receives.add(ccReceive);
                }
                receives = documentReceiveService.saveAll(receives);
                document.setReceives(receives);

                return save(document);
            }
            return document;
        }
        return null;
    }

    @Override
    public Boolean documentReceiving(DocumentReceiveDto dto) {
        User currentLoginUser = userService.getLoggedInUser();
        List<Long> accessableDepartmentIds = userService.getAccessableDepartmentIds(currentLoginUser);
        DocumentReceive receive = documentReceiveService.findById(dto.getId());
        if(accessableDepartmentIds.contains(receive.getDepartment().getId())) {
            receive.setReceiver(currentLoginUser);
            receive.setReceivedAt(LocalDateTime.now());
            receive.setDecision(dto.getDecision());
            documentReceiveService.save(receive);
            return true;
        }
        return false;
    }

    @Override
    public Boolean documentRejected(Long id) {
        User currentLoginUser = userService.getLoggedInUser();
        List<Long> accessableDepartmentIds = userService.getAccessableDepartmentIds(currentLoginUser);
        List<DocumentReceive> receives = documentReceiveService.findByDocumentIdAndDepartmentIdIn(id,
            accessableDepartmentIds);
        boolean recordFound = false;
        for (DocumentReceive receive : receives) {
            receive.setReceiver(currentLoginUser);
            receive.setReceivedAt(LocalDateTime.now());
            receive.setDecision("REJECTED");
            documentReceiveService.save(receive);
            recordFound = true;
        }
        if(recordFound) {
            return true;
        }
        return false;
    }

    @Override
    public Document updateEvaluation(Long id, DocumentEvaluationDto dto) {
        Document document = findById(id);
        // User currentLoginUser = userService.getLoggedInUser();
        Document documentToBeUpdated = DocumentMapper.mapEvaluation(dto, document, this, departmentService);
        return documentRepository.save(documentToBeUpdated);

        // DocumentExecution exec = null;
        // if (document.getReferredDepartment() == null) {
        //     exec = new DocumentExecution();
        //     exec.setDocumentExecutionType(documentExecutionTypeService.findById(2L));
        //     exec.setFromEntity(currentLoginUser.getEntity());
        //     exec.setFromDepartment(currentLoginUser.getDepartment());
        //     exec.setCreatedBy(currentLoginUser);
        //     if (dto.getCcDepartmentsIds() != null) {
        //         List<Department> ccDepartments = new ArrayList<Department>();
        //         dto.getCcDepartmentsIds().forEach((ccDepId) -> {
        //             ccDepartments.add(departmentService.findById(ccDepId));
        //         });
        //         exec.setCcDepartments(ccDepartments);
        //     }
        //     exec.setContent(dto.getContent());
        // }

        // Document documentToBeUpdated = DocumentMapper.mapEvaluation(dto, document, this, departmentService);
        // if (!documentToBeUpdated.equals(null)) {
            // Document updatedDocument = documentRepository.save(documentToBeUpdated);

            // if (exec != null) {
            //     exec.setToDepartment(document.getReferredDepartment());
            //     exec.setDocument(document);
            //     documentExecutionService.save(exec);
        //     // }
        //     return updatedDocument;
        // }
        // return null;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Document> pOptional = documentRepository.findById(id);

        if (pOptional.isPresent()) {
            Document agenda = pOptional.get();
            agenda.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            documentRepository.save(agenda);
            return true;
        }

        return false;
    }

    @Override
    public List<Document> findByCreatedByAndStatus(User user, String status) {
        return documentRepository.findByCreatedByAndStatus(user, status);
    }

    @Override
    public String saveAttachment(String uploadDirectory, MultipartFile file) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        String fileName = null;
        if (file != null) {
            fileName = formatter.format(Calendar.getInstance().getTime()) + "_" + file.getOriginalFilename();
            String saveDirectory = uploadDirectory;
            File test = new File(saveDirectory);
            if (!test.exists()) {
                test.mkdirs();
            }
            try {
                File f = new File(saveDirectory + "/" + fileName);
                // create the file
                if (!f.exists()) {

                    f.createNewFile();
                }
                FileOutputStream fout = new FileOutputStream(f);
                fout.write(file.getBytes());
                fout.close();
            } catch (Exception e) {

            }

        }
        return fileName;
    }

    @Override
    public Boolean updateFileLocation(Document document, String fileLocation) {
        if (document != null) {
            document.setAttachment(fileLocation);
            documentRepository.save(document);
            return true;
        }
        return false;
    }

    @Override
    public File downloadAttachment(Long documentId) {
        Document document = findById(documentId);
        if (document != null) {
            String file = uploadDir + "/" + document.getId().toString() + "/" + document.getAttachment();
            if (new File(file).exists()) {
                return new File(file);
            }
        }
        return null;
    }

    public Map<String, Object> countDashboardData() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("outgoing", documentRepository.countOutGoingDocuments());
        data.put("incoming", documentRepository.countIncomingDocuments());
        data.put("processed", documentRepository.countProccessedDocuments());
        data.put("underprocess", documentRepository.countUnderProcessDocuments());
        data.put("unprocessed", documentRepository.countUnProcessedDocuments());
        return data;
    }

    public Map<String, Object> countMyDashboardData(){
        Map<String, Object> data = new HashMap<String, Object>();
        Long dId = this.userService.getLoggedInUser().getDepartment().getId();
        data.put("outgoing", documentRepository.countMyDashboardOutGoingDocuments(dId));
        data.put("incoming", documentRepository.countMyDashboardIcomingDocuments(dId));
        data.put("processed", documentRepository.countMyDashboardProccessedDocuments(dId));
        data.put("underprocess", documentRepository.countMyDashboardUnderProcessDocuments(dId));
        data.put("unprocessed", documentRepository.countMyDashboardUnProcessedDocuments(dId));
        return data;
    }

    public List getDocumentCountbyEntity() {
        return documentRepository.getDocumentCountbyEntity();
    }

    public List getDocumentCountByTypeId(Long proId) {
        return documentRepository.getDocumentCountByTypeId(proId);
    }

    public List getDocumentCountbyEntityByTypeId(Long proId){
        return documentRepository.getDocumentCountbyEntityByTypeId(proId);
    }

    public List getMyDashboardDocumentCountbyEntity(){
        Long dId = this.userService.getLoggedInUser().getDepartment().getId();
        return documentRepository.getMyDashboardDocumentCountbyEntity(dId);

    }

    public List getMyDashboardDocumentCountByTypeId(Long proId){
        Long dId = this.userService.getLoggedInUser().getDepartment().getId();
        return documentRepository.getMyDashboardDocumentCountByTypeId(proId, dId);
    }

    public List getMyDashboardDocumentCountbyEntityByTypeId(Long proId){
        Long dId = this.userService.getLoggedInUser().getDepartment().getId();
        return documentRepository.getMyDashboardDocumentCountbyEntityByTypeId(proId, dId);
    }

    @Override
    public Map<String, Object> countDocumentData() {
        Map<String, Object> data = new HashMap<String, Object>();
        User currentLoginUser = userService.getLoggedInUser();
        List<Long> accessableDepartmentIds = userService.getAccessableDepartmentIds(currentLoginUser);
        data.put("processed", documentRepository.countDocuments(new ArrayList<>(List.of(3L)), accessableDepartmentIds));
        data.put("underProcess",
                documentRepository.countDocuments(new ArrayList<>(List.of(2L)), accessableDepartmentIds));
        data.put("unprocessed",
                documentRepository.countDocuments(new ArrayList<>(List.of(1L)), accessableDepartmentIds));
        data.put("all",
                documentRepository.countDocuments(new ArrayList<>(List.of(1L, 2L, 3L)), accessableDepartmentIds));
        return data;
    }

}
