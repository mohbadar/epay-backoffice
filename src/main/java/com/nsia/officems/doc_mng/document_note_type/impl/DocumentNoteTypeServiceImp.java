package com.nsia.officems.doc_mng.document_note_type.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.doc_mng.document_note_type.DocumentNoteType;
import com.nsia.officems.doc_mng.document_note_type.DocumentNoteTypeRepository;
import com.nsia.officems.doc_mng.document_note_type.DocumentNoteTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class DocumentNoteTypeServiceImp implements DocumentNoteTypeService{
    private final DocumentNoteTypeRepository repo;
    
    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private UserService userService;

    public DocumentNoteTypeServiceImp(DocumentNoteTypeRepository documentNoteTypeRepository) {
        this.repo = documentNoteTypeRepository;
    }

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " ";
        // To have first AND with no error
        String whereClause = "docNoteType.deleted is not true";
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.doc_mng_document_note_type docNoteType", null, joinClause, whereClause, groupByClause, input);
    }

    public DocumentNoteType findById(Long id) {
        System.out.println(" DocumentExecutionType.findById()" + id);
        Optional<DocumentNoteType> noteType = repo.findById(id);
        if (noteType.isPresent()) {
            System.out.println("DocumentExecutionType: " + noteType);
            return noteType.get();
        }
        return null;
    }

    public boolean delete(long id) {
        Optional<DocumentNoteType>noteType = repo.findById(id);
        if (noteType.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public List<DocumentNoteType> findAll() {
        return repo.findAll();
    }

    @Override
    public DocumentNoteType save(DocumentNoteType noteType) {
        return repo.save(noteType);
    }

    @Override
    public DocumentNoteType create(DocumentNoteType noteType) {
        noteType.setCreatedBy(userService.getLoggedInUser());
        return save(noteType);
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public boolean update(Long id, DocumentNoteType noteType) {
        Optional<DocumentNoteType> noteType1 = repo.findById(id);
        if (noteType1.isEmpty()) {
            return false;
        }

        DocumentNoteType docTypeToBeUpdated = noteType1.get();

        docTypeToBeUpdated.setNameEn(noteType.getNameEn());
        docTypeToBeUpdated.setNameDr(noteType.getNameDr());
        docTypeToBeUpdated.setNamePs(noteType.getNamePs());

        repo.save(docTypeToBeUpdated);
        return true;
    }



}
