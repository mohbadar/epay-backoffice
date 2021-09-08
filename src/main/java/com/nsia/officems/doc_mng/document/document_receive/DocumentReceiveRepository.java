package com.nsia.officems.doc_mng.document.document_receive;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DocumentReceiveRepository extends JpaRepository<DocumentReceive, Long> {
    public List<DocumentReceive> findByDocumentIdAndDepartmentIdIn(Long documentId, List<Long> departmentId);
    
    // @Query("SELECT new com.nsia.officems.doc_mng.document.document_receive.DocumentReceive(dc.id, dc.comment, dc.createdBy, dc.createdAt) from DocumentReceive dc where dc.document.id=?1")
    // public List<DocumentReceive> findByDocumentId(Long documentId);
}
