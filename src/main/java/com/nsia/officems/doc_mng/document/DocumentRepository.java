package com.nsia.officems.doc_mng.document;

import java.util.List;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.doc_mng.document.proj.DocumentAbstractViewProj;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    public List<Document> findByIdIn(List<Long> documents);
    public List<Document> findByMainDocumentIdOrderByCreatedAtDesc(Long id);

//     @Query(value = "select doc.id, doc.type, doc.title, doc.document_type_id, doc.status, doc.document_status_id, doc.form_department_id, doc.priority_type, doc.security_level, doc.created_by_id, doc.created_at from public.doc_mng_document doc WHERE doc.main_document_id=?1", nativeQuery = true)
//     public List<DocumentAbstractViewProj> getAbstractViewByMainDocumentId(Long id);

    @Query(value = "select count(*) from public.doc_mng_document", nativeQuery = true)
    public long countALL();

    @Query(value = "select count(*) from public.doc_mng_document where date(created_at) = CURRENT_DATE", nativeQuery = true)
    public long countToday();

    public List<Document> findByCreatedByAndStatus(User user, String status);

    @Query(value = "select count(*) from public.doc_mng_document where from_entity_id != 1", nativeQuery = true)
    public long countIncomingDocuments();

    @Query(value = "select count(*) from public.doc_mng_document where from_entity_id = 1", nativeQuery = true)
    public long countOutGoingDocuments();

    @Query(value = "select count(*) from public.doc_mng_document where document_status_id = 3", nativeQuery = true)
    public long countProccessedDocuments();

    @Query(value = "select count(*) from public.doc_mng_document where document_status_id = 2", nativeQuery = true)
    public long countUnderProcessDocuments();

    @Query(value = "select count(*) from public.doc_mng_document where document_status_id = 1", nativeQuery = true)
    public long countUnProcessedDocuments();

    @Query("SELECT d.fromEntity.nameDr as name , count(*) as count from Document d WHERE d.deleted is not true AND d.fromEntity is not null GROUP BY d.fromEntity.nameDr ORDER BY d.fromEntity.nameDr ASC")
    List getDocumentCountbyEntity();

    @Query(value = "SELECT d.fromEntity.nameDr as name , count(*) as count from Document d WHERE d.deleted is not true AND d.fromEntity is not null AND d.documentStatus.id =:proId GROUP BY d.fromEntity.nameDr ORDER BY d.fromEntity.nameDr ASC")
    List getDocumentCountByTypeId(@Param("proId") long proId);

    @Query("SELECT d.fromEntity.nameDr as name , count(*) as count from Document d WHERE d.deleted is not true AND d.fromEntity is not null AND d.documentType.id =:proId  GROUP BY d.fromEntity.nameDr ORDER BY d.fromEntity.nameDr ASC")
    List getDocumentCountbyEntityByTypeId(@Param("proId") long proId);

    @Query(value = "select count(id) from public.doc_mng_document doc "
            + "where doc.document_status_id in ?1 "
            +"and doc.status = 'FINAL' "
            +"and doc.type = 0 "
            +"and(doc.id in(select dmd.id from doc_mng_document dmd,user_tbl ut where dmd.from_department_id = ut.department_id and ut.id = ?2)"
            +"or doc.id in (select dmd2.id from doc_mng_document dmd2,user_tbl ut2 where dmd2.to_department_id = ut2.department_id and ut2.id = ?2)"
            +"or doc.id in (select dmdcd.document_id from doc_mng_document_cc_departments dmdcd,user_tbl ut3 where dmdcd.cc_departments_id = ut3.department_id and ut3.id = ?2))", nativeQuery = true)
    public long countMyDocuments(List<Long> statusId, Long userId);



    @Query(value = "select count(*) from public.doc_mng_document dmd "
            + "where document_status_id in ?1 and (from_department_id in ?2 or to_department_id in ?2) and dmd.status = 'FINAL'", nativeQuery = true)
    public long countDocuments(List<Long> statusId, List<Long> accessableDepartmentIds);

    @Query(value = "select count(*) from public.doc_mng_document where to_department_id =:proId", nativeQuery = true)
    public long countMyDashboardIcomingDocuments(@Param("proId") long proId);

    @Query(value = "select count(*) from public.doc_mng_document where from_department_id =:proId", nativeQuery = true)
    public long countMyDashboardOutGoingDocuments(@Param("proId") long proId);


    @Query(value = "select count(*) from public.doc_mng_document where document_status_id = 3 and to_department_id =:proId", nativeQuery = true)
    public long countMyDashboardProccessedDocuments(@Param("proId") long proId);

    @Query(value = "select count(*) from public.doc_mng_document where document_status_id = 2  and to_department_id =:proId", nativeQuery = true)
    public long countMyDashboardUnderProcessDocuments(@Param("proId") long proId);

    @Query(value = "select count(*) from public.doc_mng_document where document_status_id = 1  and to_department_id =:proId", nativeQuery = true)
    public long countMyDashboardUnProcessedDocuments(@Param("proId") long proId);

    @Query("SELECT d.fromEntity.nameDr as name , count(*) as count from Document d WHERE d.deleted is not true AND d.fromEntity is not null AND d.toDepartment.id =:proId GROUP BY d.fromEntity.nameDr ORDER BY d.fromEntity.nameDr ASC")
    List getMyDashboardDocumentCountbyEntity(@Param("proId") long proId);


    @Query("SELECT d.fromEntity.nameDr as name , count(*) as count from Document d WHERE d.deleted is not true AND d.fromEntity is not null AND d.documentType.id =:proId AND d.toDepartment.id =:dId GROUP BY d.fromEntity.nameDr ORDER BY d.fromEntity.nameDr ASC")
    List getMyDashboardDocumentCountbyEntityByTypeId(@Param("proId") long proId, @Param("dId") long dId);

    @Query(value = "SELECT d.fromEntity.nameDr as name , count(*) as count from Document d WHERE d.deleted is not true AND d.fromEntity is not null AND d.documentStatus.id =:proId AND d.toDepartment.id =:dId GROUP BY d.fromEntity.nameDr ORDER BY d.fromEntity.nameDr ASC")
    List getMyDashboardDocumentCountByTypeId(@Param("proId") long proId, @Param("dId") long dId);

}