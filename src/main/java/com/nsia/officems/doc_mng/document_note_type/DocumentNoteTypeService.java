package com.nsia.officems.doc_mng.document_note_type;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface DocumentNoteTypeService {
    
    public Object getList(DataTablesInput input, Map<String, String> filters); 
    public DocumentNoteType save(DocumentNoteType visit);
    public List<DocumentNoteType> findAll();
    public DocumentNoteType findById(Long id);
    public DocumentNoteType create(DocumentNoteType note);
    public Boolean delete(Long id);
    public boolean update(Long id, DocumentNoteType note);
}
