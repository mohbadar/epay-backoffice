package com.nsia.officems.doc_mng.document.document_review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DocumentReviewRepository extends JpaRepository<DocumentReview, Long> {
    // @Query("SELECT new com.nsia.officems.doc_mng.document.document_review.DocumentReview(dc.id, dc.comment, dc.createdBy, dc.createdAt) from DocumentReview dc where dc.document.id=?1")
    // public List<DocumentReview> findByDocumentId(Long documentId);
}
