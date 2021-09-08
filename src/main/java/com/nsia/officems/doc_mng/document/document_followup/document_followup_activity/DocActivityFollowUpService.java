package com.nsia.officems.doc_mng.document.document_followup.document_followup_activity;

import com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.DocFollowUpActivity;
import com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.dto.DocFollowUpActivityDTO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public interface DocActivityFollowUpService {

    DocFollowUpActivity findTopByIsFinalAndDocumentFollowUp_idOrderByCreatedAtDesc(Boolean isFinal, Long docFollowUpId);
    DocFollowUpActivity create(DocFollowUpActivityDTO dto, MultipartFile file);
    DocFollowUpActivityDTO update(DocFollowUpActivityDTO dto, Long id, MultipartFile file);
    Boolean deleteDocFollowUpActivity(Long docActivityNo);
    DocFollowUpActivityDTO getDetails(Long id);
    File downloadAttachment(Long id) throws Exception;
    DocFollowUpActivityDTO finalizeActivity(Long id);
}
