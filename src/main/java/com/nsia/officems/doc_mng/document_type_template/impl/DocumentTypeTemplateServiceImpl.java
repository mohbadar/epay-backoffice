package com.nsia.officems.doc_mng.document_type_template.impl;

import com.nsia.officems.doc_mng.document_type.DocumentTypeService;
import com.nsia.officems.doc_mng.document_type_template.DocumentTypeTemplate;
import com.nsia.officems.doc_mng.document_type_template.DocumentTypeTemplateRepository;
import com.nsia.officems.doc_mng.document_type_template.DocumentTypeTemplateService;
import com.nsia.officems.doc_mng.document_type_template.dto.DocumentTypeTemplateDto;
import com.nsia.officems.doc_mng.document_type_template.dto.DocumentTypeTemplateMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class DocumentTypeTemplateServiceImpl implements DocumentTypeTemplateService {
    private final DocumentTypeTemplateRepository documentTypeTemplateRepository;

    @Autowired
    private DocumentTypeService documentTypeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    public DocumentTypeTemplateServiceImpl(DocumentTypeTemplateRepository documentTypeTemplateRepository) {
        this.documentTypeTemplateRepository = documentTypeTemplateRepository;
    }

    @Override
    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " inner join public.department edep on edep.id=docTypeTemp.entity_id ";
        joinClause += " inner join public.department dep on dep.id=docTypeTemp.department_id ";
        joinClause += " inner join public.doc_mng_document_type doc_type on doc_type.id=docTypeTemp.document_type_id ";
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.doc_mng_document_type_template docTypeTemp", null, joinClause,
                whereClause, groupByClause, input);
    }

    public DocumentTypeTemplate findById(Long id) {
        System.out.println(" DocumentTypeTemplate.findById()" + id);
        Optional<DocumentTypeTemplate> documentTypeTemplate = documentTypeTemplateRepository.findById(id);
        if (documentTypeTemplate.isPresent()) {
            System.out.println("DocumentTypeTemplate: " + documentTypeTemplate);
            return documentTypeTemplate.get();
        }
        return null;
    }

    @Transactional
    public DocumentTypeTemplate findByDocumentTypeIdAndEntityId(Long docTypeId, Long entityId) {
        return documentTypeTemplateRepository.findByDocumentTypeIdAndEntityId(docTypeId, entityId);
    }

    public boolean delete(long id) {
        Optional<DocumentTypeTemplate> documentTypeTemplate = documentTypeTemplateRepository.findById(id);
        if (documentTypeTemplate.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public List<DocumentTypeTemplate> findAll() {
        return documentTypeTemplateRepository.findAll();
    }

    @Override
    public DocumentTypeTemplate save(DocumentTypeTemplate documentTypeTemplate) {
        return documentTypeTemplateRepository.save(documentTypeTemplate);
    }

    @Override
    public DocumentTypeTemplate create(DocumentTypeTemplateDto dto) {
        DocumentTypeTemplate documentTypeTemplate = new DocumentTypeTemplate();
        documentTypeTemplate = DocumentTypeTemplateMapper.map(dto, documentTypeTemplate, this, departmentService,
                documentTypeService, userService);
        if (!documentTypeTemplate.equals(null)) {
            documentTypeTemplate.setDocumentType(documentTypeService.findById(dto.getDocumentTypeId()));
            return documentTypeTemplateRepository.save(documentTypeTemplate);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public boolean update(Long id, DocumentTypeTemplateDto dto) {
        DocumentTypeTemplate documentTypeTemplate = findById(id);
        documentTypeTemplate = DocumentTypeTemplateMapper.map(dto, documentTypeTemplate, this, departmentService,
                documentTypeService, userService);
        if (!documentTypeTemplate.equals(null)) {
            save(documentTypeTemplate);
            return true;
        }
        return false;
    }

}
