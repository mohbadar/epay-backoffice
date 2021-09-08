package com.nsia.officems.doc_mng.document.document_followup;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DocumentFollowUpRepository extends JpaRepository<DocumentFollowUp, Long>{
//    public List<DocumentFollowUp> findByDocument_idAndDeletedIsFalseOrDeletedIsNullOrderByDateDesc(Long id);
    
    @Query(value = "select * from public.doc_mng_document_follow_up where deleted is not true order by id desc", nativeQuery = true)
    public List<DocumentFollowUp> findAllWithDeletedNull();

    public List<DocumentFollowUp> findByDocumentId(Long documentId);

    public List<DocumentFollowUp> findByDocumentIdOrderByCreatedAtAsc(Long documentId);

    List<DocumentFollowUp> findByDocumentIdAndDepartmentId(Long documentId, Long createdDepId);

    @Query(value = "select * from doc_mng_document_follow_up dmdf where dmdf.document_id = ?1 and (department_id = ?2 or assigned_to = ?3)", nativeQuery = true)
    List<DocumentFollowUp> findByDocAndDepartmentAndAssignedUser(Long documentId, Long createdDepId, Long assignedUserId);

    //    @Query("SELECT od.type.nameDr as name , count(*) as count from DocumentFollowUp od WHERE od.deleted is not true and od.document.id=:docId GROUP BY od.type.nameDr ORDER BY od.type.nameDr")
    @Query("SELECT count(*) as count from DocumentFollowUp od WHERE od.deleted is not true and od.document.id=:docId")
    List getFollowUpCountByType(@Param("docId") long docId);
}
