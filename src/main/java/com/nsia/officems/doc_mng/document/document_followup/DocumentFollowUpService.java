package com.nsia.officems.doc_mng.document.document_followup;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.DocFollowUpActivity;
import com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.dto.DocFollowUpActivityDTO;
import com.nsia.officems.doc_mng.document.document_followup.dto.DocumentFollowUpDto;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentFollowUpService {
    public List<DocumentFollowUp> findAll();

    public DocumentFollowUp findById(Long id);

    public DocFollowUpActivity create(DocFollowUpActivityDTO dto, MultipartFile file);

    public Boolean delete(Long id);

    public Boolean update(Long id, DocumentFollowUpDto dto, MultipartFile file);

    public List<DocumentFollowUpDto> findByDocument_id(Long id);

    public List getFollowUpCountByType(Long id);

    public File downloadAttachment(Long id) throws Exception;

    public DocumentFollowUpDto getDetails(Long id);

    public List<DocumentFollowUp> assignUsers(String dueDate, List<Long> userIds, Long docNo);

    public DocumentFollowUp assignUser(String dueDateString, Long userId, Long docNo);

    public Map<String, Object> updateDueDate(Long id, String dueDate);

}
