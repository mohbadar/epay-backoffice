package com.nsia.officems._admin.issuing_authority;

import com.nsia.officems.base.LookupProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IssuingAuthorityRepository extends JpaRepository<IssuingAuthority, Long> {

    public List<IssuingAuthority> findByDeletedFalseOrDeletedIsNull();
    @Query(value = "select id as value, name_dr as name from doc_mng_document_category where id = ?1", nativeQuery = true)
    public LookupProjection findByIdDr(Long id);

    @Query(value = "select id as value, name_ps as name from doc_mng_document_category where id = ?1", nativeQuery = true)
    public LookupProjection findByIdPs(Long id);

    @Query(value = "select id as value, name_en as name from doc_mng_document_category where id = ?1", nativeQuery = true)
    public LookupProjection findByIdEn(Long id);

    @Query(value="select id as value, name_dr as name from doc_mng_document_category", nativeQuery = true)
    public List<LookupProjection> findAllDr();

    @Query(value="select id as value, name_ps as name from doc_mng_document_category", nativeQuery = true)
    public List<LookupProjection> findAllPs();

    @Query(value="select id as value, name_en as name from doc_mng_document_category", nativeQuery = true)
    public List<LookupProjection> findAllEn();
}
