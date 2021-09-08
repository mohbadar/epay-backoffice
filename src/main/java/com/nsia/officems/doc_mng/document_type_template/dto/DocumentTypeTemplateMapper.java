package com.nsia.officems.doc_mng.document_type_template.dto;

import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.doc_mng.document_type.DocumentTypeService;
import com.nsia.officems.doc_mng.document_type_template.DocumentTypeTemplate;
import com.nsia.officems.doc_mng.document_type_template.DocumentTypeTemplateService;

public class DocumentTypeTemplateMapper {

    public static DocumentTypeTemplate map(DocumentTypeTemplateDto dto, DocumentTypeTemplate documentTypeTemplate,
            DocumentTypeTemplateService documentTypeTemplateService, DepartmentService departmentService,
            DocumentTypeService documentTypeService, UserService userService) {
        DateTimeChange changeDate = new DateTimeChange();

        try {
            documentTypeTemplate.setTemplate(dto.getTemplate());
            documentTypeTemplate
                    .setEntity(dto.getEntityId() == null ? null : departmentService.findById(dto.getEntityId()));
            documentTypeTemplate.setDepartment(
                    dto.getDepartmentId() == null ? null : departmentService.findById(dto.getDepartmentId()));
            documentTypeTemplate.setDocumentType(
                    dto.getDocumentTypeId() == null ? null : documentTypeService.findById(dto.getDocumentTypeId()));
            if (documentTypeTemplate.getCreatedBy() == null) {
                documentTypeTemplate.setCreatedBy(userService.getLoggedInUser());
            }

            return documentTypeTemplate;

        } catch (Exception e) {
            System.out.println("Exception occured in mapping");
            return null;
        }

    }
}
