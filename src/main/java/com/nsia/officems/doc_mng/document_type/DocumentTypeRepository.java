package com.nsia.officems.doc_mng.document_type;

import java.util.List;

import com.nsia.officems.doc_mng.document_type.proj.DocumentTypeLookupProj;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {
    public List<DocumentType> findByTypeIn(List<Long> type);

    @Query(value = "SELECT doc_type.id, doc_type.name_dr as name, doc_type.slug, doc_type.type FROM doc_mng_document_type doc_type", nativeQuery = true)
    public List<DocumentTypeLookupProj> findAllDocumentTypes();

    @Query(value = "SELECT doc_type.id, doc_type.name_dr as name, doc_type.slug, doc_type.type FROM doc_mng_document_type doc_type where doc_type.type in (?1)", nativeQuery = true)
    public List<DocumentTypeLookupProj> findByTypeDr(List<Long> types);

    @Query(value = "SELECT doc_type.id, doc_type.name_ps as name, doc_type.slug, doc_type.type FROM doc_mng_document_type doc_type where doc_type.type in (?1)", nativeQuery = true)
    public List<DocumentTypeLookupProj> findByTypePs(List<Long> types);

    @Query(value = "SELECT doc_type.id, doc_type.name_en as name, doc_type.slug, doc_type.type FROM doc_mng_document_type doc_type where doc_type.type in (?1)", nativeQuery = true)
    public List<DocumentTypeLookupProj> findByTypeEn(List<Long> types);
}