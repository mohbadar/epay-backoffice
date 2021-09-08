package com.nsia.officems.doc_mng.document_type_template;

import java.util.List;
import java.util.Map;

import com.nsia.officems.doc_mng.document_type_template.dto.DocumentTypeTemplateDto;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface DocumentTypeTemplateService {
    public DocumentTypeTemplate save(DocumentTypeTemplate visit);
    public List<DocumentTypeTemplate> findAll();
    public Object getList(DataTablesInput input, Map<String, String> filters);
    public DocumentTypeTemplate findById(Long id);
    public DocumentTypeTemplate findByDocumentTypeIdAndEntityId(Long docTypeId, Long entityId);
    public DocumentTypeTemplate create(DocumentTypeTemplateDto documentTypeTemplateDto);
    public Boolean delete(Long id);
    public boolean update(Long id, DocumentTypeTemplateDto documentTypeTemplateDto);
}
