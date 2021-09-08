package com.nsia.officems.doc_mng.document_status.impl;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import com.nsia.officems.doc_mng.document_status.DocumentStatus;
import com.nsia.officems.doc_mng.document_status.DocumentStatusRepository;
import com.nsia.officems.doc_mng.document_status.DocumentStatusService;

import java.time.ZoneId;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class DocumentStatusServiceImpl implements DocumentStatusService{
    @Autowired
    private DocumentStatusRepository repo;

    @Override
    public List<DocumentStatus> findAll() {
        return repo.findAll();
    }

    @Override
    public DocumentStatus findById(Long id) {
        Optional<DocumentStatus> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public DocumentStatus create(DocumentStatus type) {
        return repo.save(type);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<DocumentStatus> type = repo.findById(id);

        if (type.isPresent()) {
            DocumentStatus type2 = type.get();
            type2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            type2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            repo.save(type2);
            return true;
        }

        return false;
    }
}
