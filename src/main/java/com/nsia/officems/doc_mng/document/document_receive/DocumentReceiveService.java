package com.nsia.officems.doc_mng.document.document_receive;

import java.io.File;
import java.util.List;

import com.nsia.officems.doc_mng.document.document_receive.dto.DocumentReceiveDto;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentReceiveService {
    public DocumentReceive findById(Long id);
    public DocumentReceive save(DocumentReceive executionReview);
    public List<DocumentReceive> saveAll(List<DocumentReceive> executionReviews);
    public DocumentReceive create(DocumentReceive executionReview);
    public List<DocumentReceive> findByDocumentIdAndDepartmentIdIn(Long docId, List<Long> depIds);
    // public DocumentReceive updateReviewDecision(Long id, DocumentReceiveDto dto);



    public List<DocumentReceive> findByDocumentId(Long id);
    // public DocumentReceive create(String data, MultipartFile file);
    public DocumentReceive create(DocumentReceiveDto documentCommentDto);
    public Boolean update(Long id, String data, MultipartFile file);

    public Boolean delete(Long id);
}
