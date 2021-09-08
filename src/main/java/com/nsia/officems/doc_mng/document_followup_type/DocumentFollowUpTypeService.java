package com.nsia.officems.doc_mng.document_followup_type;

import java.util.List;

public interface DocumentFollowUpTypeService {
    public List<DocumentFollowUpType> findAll();
    public DocumentFollowUpType findById(Long id);
    public DocumentFollowUpType create(DocumentFollowUpType type);
    public Boolean delete(Long id);
}
