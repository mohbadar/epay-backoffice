package com.nsia.officems.doc_mng.document.document_comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DocumentCommentRepository extends JpaRepository<DocumentComment, Long> {
    @Query("SELECT new com.nsia.officems.doc_mng.document.document_comment.DocumentComment(dc.id, dc.comment, dc.date, dc.createdBy, dc.createdAt) from DocumentComment dc where dc.document.id=?1")
    public List<DocumentComment> findByDocumentId(Long documentId);
}
