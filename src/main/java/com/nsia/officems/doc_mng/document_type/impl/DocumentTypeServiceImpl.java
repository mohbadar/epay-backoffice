package com.nsia.officems.doc_mng.document_type.impl;

import com.nsia.officems.doc_mng.document_type.DocumentType;
import com.nsia.officems.doc_mng.document_type.DocumentTypeRepository;
import com.nsia.officems.doc_mng.document_type.DocumentTypeService;
import com.nsia.officems.doc_mng.document_type.proj.DocumentTypeLookupProj;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Arrays;
import java.util.ArrayList;

import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {
    private final DocumentTypeRepository documentTypeRepository;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private UserService userService;

    public DocumentTypeServiceImpl(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " ";
        // To have first AND with no error
        String whereClause = "doctype.deleted is not true";
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.doc_mng_document_type doctype", null, joinClause, whereClause,
                groupByClause, input);
    }

    public DocumentType findById(Long id) {
        System.out.println(" DocumentType.findById()" + id);
        Optional<DocumentType> documentType = documentTypeRepository.findById(id);
        if (documentType.isPresent()) {
            System.out.println("DocumentType: " + documentType);
            return documentType.get();
        }
        return null;
    }

    public boolean delete(long id) {
        Optional<DocumentType> documentType = documentTypeRepository.findById(id);
        if (documentType.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public List<DocumentTypeLookupProj> findAll() {
        return documentTypeRepository.findAllDocumentTypes();
    }

    @Override
    public List<DocumentTypeLookupProj> findByLangAndType(List<Long> types) {
        String lang = customUserService.getCurrentLang();
        switch (lang) {
            case "dr":
                return documentTypeRepository.findByTypeDr(types);
            case "en":
                return documentTypeRepository.findByTypeEn(types);
            default:
                return documentTypeRepository.findByTypePs(types);
        }
    }

    @Override
    public List<DocumentTypeLookupProj> findByType(String type) {
        if (type.equals("document")) {
            return findByLangAndType(new ArrayList<Long>(Arrays.asList(1L, 3L)));
            // return documentTypeRepository.findByTypeIn(new
            // ArrayList<Long>(Arrays.asList(1L, 3L)));
        } else if (type.equals("execution")) {
            return findByLangAndType(new ArrayList<Long>(Arrays.asList(2L, 3L)));
            // return documentTypeRepository.findByTypeIn(new
            // ArrayList<Long>(Arrays.asList(2L, 3L)));
        }
        return null;

    }

    @Override
    public DocumentType save(DocumentType documentType) {
        return documentTypeRepository.save(documentType);
    }

    @Override
    public DocumentType create(DocumentType documentType) {
        documentType.setCreatedBy(userService.getLoggedInUser());
        return save(documentType);
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public boolean update(Long id, DocumentType documentType) {
        Optional<DocumentType> docType = documentTypeRepository.findById(id);
        if (docType.isEmpty()) {
            return false;
        }

        DocumentType docTypeToBeUpdated = docType.get();

        docTypeToBeUpdated.setNameEn(documentType.getNameEn());
        docTypeToBeUpdated.setNameDr(documentType.getNameDr());
        docTypeToBeUpdated.setNamePs(documentType.getNamePs());

        documentTypeRepository.save(docTypeToBeUpdated);
        return true;
    }

}
