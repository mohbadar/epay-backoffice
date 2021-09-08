package com.nsia.officems.doc_mng.document;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.doc_mng.document.document_receive.dto.DocumentReceiveDto;
import com.nsia.officems.doc_mng.document.dto.DocumentDto;
import com.nsia.officems.doc_mng.document.dto.DocumentEvaluationDto;
import com.nsia.officems.doc_mng.document.proj.DocumentAbstractViewProj;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
    public Document save(Document document);

    public List<Document> findAll();

    public Document findById(Long id);
    public List<DocumentAbstractViewProj> findByMainDocumentId(Long id);

    public Document create(DocumentDto dto);

    public Boolean delete(Long id);

    public Document update(Long id, DocumentDto dto);

    public Document finalizeDocumentStatus(Long id);

    public Document setDocumentStatusExecuted(Long id);

    public Boolean documentReceiving(DocumentReceiveDto dto);
    public Boolean documentRejected(Long id);

    public Document updateEvaluation(Long id, DocumentEvaluationDto dto);

    public Object getList(DataTablesInput input, Map<String, String> filters);

    public Object getFollowupList(DataTablesInput input, Map<String, String> filters);

    public List<Document> findbyIdIn(List<Long> ids);

    public List<Document> findByCreatedByAndStatus(User user, String status);

    public String saveAttachment(String uploadDirectory, MultipartFile file);

    public Boolean updateFileLocation(Document document, String fileLocation);

    public File downloadAttachment(Long documentId);

    public Map<String, Object> countDashboardData();

    public List getDocumentCountbyEntity();

    public List getDocumentCountByTypeId(Long proId);

    public List getDocumentCountbyEntityByTypeId(Long proId);

    public Map<String, Object> countDocumentData();
    public Map<String, Object> countMyDashboardData();

    public List getMyDashboardDocumentCountbyEntity();

    public List getMyDashboardDocumentCountByTypeId(Long proId);

    public List getMyDashboardDocumentCountbyEntityByTypeId(Long proId);

}
