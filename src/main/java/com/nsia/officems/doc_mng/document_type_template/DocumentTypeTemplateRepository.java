package com.nsia.officems.doc_mng.document_type_template;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeTemplateRepository extends JpaRepository<DocumentTypeTemplate, Long>  {
    DocumentTypeTemplate findByDocumentTypeIdAndEntityId(Long docTypeId, Long entityId);
}