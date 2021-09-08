package com.nsia.officems.doc_mng.document_type;

import java.util.List;
import java.util.Map;

import com.nsia.officems.doc_mng.document_type.proj.DocumentTypeLookupProj;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface DocumentTypeService {

    public Object getList(DataTablesInput input, Map<String, String> filters);

    public DocumentType save(DocumentType visit);

    public List<DocumentTypeLookupProj> findAll();

    public List<DocumentTypeLookupProj> findByLangAndType(List<Long> types);

    public DocumentType findById(Long id);

    public List<DocumentTypeLookupProj> findByType(String type);

    public DocumentType create(DocumentType documentType);

    public Boolean delete(Long id);

    public boolean update(Long id, DocumentType documentType);
}
