package com.nsia.officems.doc_mng.document.document_attachment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentAttachmentRepository extends JpaRepository<DocumentAttachment, Long> {
    public List<DocumentAttachment> findByDocument_idAndDeletedFalse(Long id);
}