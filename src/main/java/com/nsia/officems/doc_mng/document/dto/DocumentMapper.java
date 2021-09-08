package com.nsia.officems.doc_mng.document.dto;

import java.util.ArrayList;
import java.util.List;

import com.nsia.officems._admin.department.Department;
import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._admin.district.District;
import com.nsia.officems._admin.district.DistrictService;
import com.nsia.officems._admin.document_category.DocumentCategoryService;
import com.nsia.officems._admin.issuing_authority.IssuingAuthorityService;
import com.nsia.officems._admin.province.Province;
import com.nsia.officems._admin.province.ProvinceService;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.doc_mng.document.Document;
import com.nsia.officems.doc_mng.document.DocumentService;
import com.nsia.officems.doc_mng.document.document_note_section.DocumentNoteSection;
import com.nsia.officems.doc_mng.document.document_review.DocumentReview;
import com.nsia.officems.doc_mng.document_note_type.DocumentNoteTypeService;
import com.nsia.officems.doc_mng.document_type.DocumentTypeService;

public class DocumentMapper {

        public static Document map(DocumentDto dto, Document document, DocumentService documentService,
                        DepartmentService departmentService, DocumentTypeService documentTypeService,
                        UserService userService, IssuingAuthorityService issuingAuthorityService,
                        DocumentCategoryService documentCategoryService, ProvinceService provinceService,
                        DistrictService districtService, DocumentNoteTypeService noteTypeService) {
                User currentLoginUser = userService.getLoggedInUser();
                DateTimeChange changeDate = new DateTimeChange();
                if (document == null)
                        document = new Document();

                if (document.getId() == null) {
                        document.setType(dto.getType());

                        document.setMainDocument(dto.getMainDocumentId() == null ? null
                                        : documentService.findById(dto.getMainDocumentId()));
                        document.setDocumentType(dto.getDocumentTypeId() == null ? null
                                        : documentTypeService.findById(dto.getDocumentTypeId()));
                }
                User currentLoginUsed = userService.getLoggedInUser();

                document.setDocumentNo(dto.getDocumentNo());
                document.setDocumentDate(dto.getDocumentDate() == null ? null
                                : changeDate.convertPersianDateToGregorianDate(dto.getDocumentDate()));
                document.setFromEntity(dto.getFromEntityId() == null ? null
                                : departmentService.findById(dto.getFromEntityId()));
                document.setFromDepartment(dto.getFromDepartmentId() == null ? null
                                : departmentService.findById(dto.getFromDepartmentId()));
                document.setToDepartment(dto.getToDepartmentId() == null ? null
                                : departmentService.findById(dto.getToDepartmentId()));
                document.setOfferorDepartment(dto.getOfferorDepartment() == null ? null
                        : departmentService.findById(dto.getOfferorDepartment()));

                if (dto.getCcDepartmentsIds() != null) {
                        List<Department> ccDepartments = new ArrayList<Department>();
                        dto.getCcDepartmentsIds().forEach((id) -> {
                                ccDepartments.add(departmentService.findById(id));
                        });
                        document.setCcDepartments(ccDepartments);
                }

                if (dto.getLinkedDocument() != null) {
                        List<Document> linkedDocument = new ArrayList<Document>();
                        linkedDocument.add(documentService.findById(dto.getLinkedDocument()));
                        document.setLinkedDocuments(linkedDocument);
                }

                document.setTitle(dto.getTitle());
                document.setContent(dto.getContent());

                document.setDocumentPriorityType(dto.getPriorityType());
                document.setDocumentSecurityLevel(dto.getSecurityLevel());

                document.setCreatedBy(currentLoginUsed);
                document.setFromEntity(dto.getFromEntityId() == null ? null
                                : departmentService.findById(dto.getFromEntityId()));
                document.setScope(dto.getScope());

                document.setNoteType(dto.getNoteType() == null ? null : noteTypeService.findById(dto.getNoteType()));

                if (dto.getReviewerIds() != null) {
                        List<DocumentReview> reviews = new ArrayList<DocumentReview>();
                        dto.getReviewerIds().forEach((reviewerId) -> {
                                DocumentReview review = new DocumentReview();
                                review.setReviewer(userService.findById(reviewerId));
                                review.setCreatedBy(currentLoginUser);
                                reviews.add(review);
                        });
                        // List<DocumentExecutionReview> createdReviews =
                        // executionReviewService.saveAll(reviews);
                        document.setReviews(reviews);
                }

                if (dto.getNoteSections() != null) {
                        List<DocumentNoteSection> noteSections = new ArrayList<DocumentNoteSection>();
                        dto.getNoteSections().forEach((noteSectionDto) -> {
                                DocumentNoteSection newNoteSection= new DocumentNoteSection();
                                newNoteSection.setTitle(noteSectionDto.getTitle());
                                newNoteSection.setContent(noteSectionDto.getContent());
                                newNoteSection.setOrderCol(noteSectionDto.getOrderCol());
                                noteSections.add(newNoteSection);
                        });
                        document.setNoteSections(noteSections);
                }

                document.setImplementationEndDate(dto.getImplementationEndDate() == null ? null
                                : changeDate.convertPersianDateToGregorianDate(dto.getImplementationEndDate()));
                document.setIssuingAuthority(dto.getIssuingAuthority() == null ? null
                                : issuingAuthorityService.findById(dto.getIssuingAuthority()));

                document.setCategory(
                                dto.getCategory() == null ? null : documentCategoryService.findById(dto.getCategory()));
                document.setOfferNo(dto.getOfferNo());

                document.setVerbal(dto.getVerbal());
                document.setDirectorInstruction(dto.getDirectorInstruction());
                document.setMaktobNo(dto.getMaktobNo());

                if (dto.getGuidanceProvinces() != null) {
                        List<Province> provinces = new ArrayList<Province>();
                        dto.getGuidanceProvinces().forEach((provinceId) -> {
                                Province province = provinceService.findById(provinceId);
                                provinces.add(province);
                        });

                        document.setGuidanceProvinces(provinces);
                }

                return document;
        }

        public static Document mapEvaluation(DocumentEvaluationDto dto, Document document,
                        DocumentService documentService, DepartmentService departmentService) {
                DateTimeChange changeDate = new DateTimeChange();
                if (document == null)
                        document = new Document();

                Boolean followup = dto.getFollowup();
                document.setFollowup(followup);

                if (!followup) {
                        document.setDueDate(null);
                } else {
                        document.setDueDate(dto.getDueDate() == null ? null
                                        : changeDate.convertPersianDateToGregorianDate(dto.getDueDate()));
                }

                // we skip if the reffered department is already set for document
                // if(document.getReferredDepartment() == null) {
                // document.setReferredDepartment(dto.getReferredDepartmentId() == null ? null
                // : departmentService.findById(dto.getReferredDepartmentId()));
                // }

                return document;
        }
}
