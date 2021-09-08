package com.nsia.officems.doc_mng.document_status;

import java.util.List;

public interface DocumentStatusService {
    public List<DocumentStatus> findAll();
    public DocumentStatus findById(Long id);
    public DocumentStatus create(DocumentStatus type);
    public Boolean delete(Long id);
}
