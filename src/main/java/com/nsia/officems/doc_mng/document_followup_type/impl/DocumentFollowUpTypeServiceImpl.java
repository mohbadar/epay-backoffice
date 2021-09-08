package com.nsia.officems.doc_mng.document_followup_type.impl;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import com.nsia.officems.doc_mng.document_followup_type.DocumentFollowUpType;
import com.nsia.officems.doc_mng.document_followup_type.DocumentFollowUpTypeRepository;
import com.nsia.officems.doc_mng.document_followup_type.DocumentFollowUpTypeService;

import java.time.ZoneId;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class DocumentFollowUpTypeServiceImpl implements DocumentFollowUpTypeService{
    @Autowired
    private DocumentFollowUpTypeRepository repo;

    @Override
    public List<DocumentFollowUpType> findAll() {
        return repo.findAll();
    }

    @Override
    public DocumentFollowUpType findById(Long id) {
        Optional<DocumentFollowUpType> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public DocumentFollowUpType create(DocumentFollowUpType type) {
        return repo.save(type);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<DocumentFollowUpType> type = repo.findById(id);

        if (type.isPresent()) {
            DocumentFollowUpType type2 = type.get();
            type2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            type2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            repo.save(type2);
            return true;
        }

        return false;
    }
}
