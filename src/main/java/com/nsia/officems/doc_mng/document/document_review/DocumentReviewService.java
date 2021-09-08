package com.nsia.officems.doc_mng.document.document_review;

import java.io.File;
import java.util.List;

import com.nsia.officems.doc_mng.document.document_review.dto.DocumentReviewDto;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentReviewService {
    public DocumentReview findById(Long id);
    public DocumentReview save(DocumentReview executionReview);
    public List<DocumentReview> saveAll(List<DocumentReview> executionReviews);
    public DocumentReview create(DocumentReview executionReview);
    public DocumentReview updateReviewDecision(Long id, DocumentReviewDto dto);
    public DocumentReview resetReviewDecision(Long id);



    public List<DocumentReview> findByDocumentId(Long id);
    // public DocumentReview create(String data, MultipartFile file);
    public DocumentReview create(DocumentReviewDto documentCommentDto);
    public Boolean update(Long id, String data, MultipartFile file);

    public Boolean delete(Long id);
}
