package com.nsia.officems.doc_mng.document_followup_status.impl;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import com.nsia.officems.doc_mng.document_followup_status.DocumentFollowUpStatus;
import com.nsia.officems.doc_mng.document_followup_status.DocumentFollowUpStatusRepository;
import com.nsia.officems.doc_mng.document_followup_status.DocumentFollowUpStatusService;

import java.time.ZoneId;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class DocumentFollowUpStatusServiceImpl implements DocumentFollowUpStatusService{
    @Autowired
    private DocumentFollowUpStatusRepository repo;

    @Override
    public List<DocumentFollowUpStatus> findAll() {
        return repo.findAll();
    }

    @Override
    public DocumentFollowUpStatus findById(Long id) {
        Optional<DocumentFollowUpStatus> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public DocumentFollowUpStatus create(DocumentFollowUpStatus type) {
        return repo.save(type);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<DocumentFollowUpStatus> type = repo.findById(id);

        if (type.isPresent()) {
            DocumentFollowUpStatus type2 = type.get();
            type2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            type2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            repo.save(type2);
            return true;
        }

        return false;
    }
}
