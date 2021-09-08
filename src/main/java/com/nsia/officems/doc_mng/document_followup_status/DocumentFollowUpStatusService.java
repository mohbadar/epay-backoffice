package com.nsia.officems.doc_mng.document_followup_status;

import java.util.List;

public interface DocumentFollowUpStatusService {
    public List<DocumentFollowUpStatus> findAll();
    public DocumentFollowUpStatus findById(Long id);
    public DocumentFollowUpStatus create(DocumentFollowUpStatus type);
    public Boolean delete(Long id);
}
