package com.nsia.officems.doc_mng.document.document_followup.dto;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.doc_mng.document.Document;
import com.nsia.officems.doc_mng.document.document_followup.DocumentFollowUp;
import com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.DocFollowUpActivity;
import com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.dto.DocFollowUpActivityDTO;
import com.nsia.officems.doc_mng.document_followup_status.DocumentFollowUpStatus;
import com.nsia.officems.doc_mng.document_followup_type.DocumentFollowUpType;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class DocumentFollowUpMapper {

    public static DocumentFollowUp map(DocumentFollowUp documentFollowUp, User assignedUser, Date dueDate, Document document, DocumentFollowUpStatus documentFollowUpStatus, User createUser) {

        if (documentFollowUp.getId() == null) {
            documentFollowUp = new DocumentFollowUp();
        }

        documentFollowUp.setDocument(document);
        documentFollowUp.setAssignedTo(assignedUser);
        documentFollowUp.setFollowupStatus(documentFollowUpStatus);
        documentFollowUp.setCreatedBy(createUser);
        documentFollowUp.setDepartment(createUser.getDepartment());
        if(dueDate != null) {
            documentFollowUp.setDueDate(Instant.ofEpochMilli(dueDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime());
        }

        return documentFollowUp;
    }


    public static DocFollowUpActivity mapDocFollowUpActivity(DocFollowUpActivity activity, DocFollowUpActivityDTO dto, DocumentFollowUp documentFollowUp, DocumentFollowUpStatus documentFollowUpStatus, DocumentFollowUpType documentFollowUpType) {
        activity.setSummary(dto.getSummary() == null ? null : dto.getSummary());
        activity.setDocumentFollowUp(documentFollowUp);
        activity.setFollowupStatus(documentFollowUpStatus);
        activity.setDocumentFollowUpType(documentFollowUpType);
        activity.setIsFinal(false);

        return activity;
    }

    public static DocumentFollowUpDto mapDTO(DocumentFollowUp documentFollowUp, List<DocFollowUpActivityDTO> docFollowUpActivityDTOS, DocFollowUpActivity topDocFlActivity, String lang) {
        DocumentFollowUpDto dto = new DocumentFollowUpDto();

        dto.setId(documentFollowUp.getId());
        dto.setDocumentId(documentFollowUp.getDocument().getId());
        dto.setAssignedTo(documentFollowUp.getAssignedTo().getId());
        dto.setFollowupStatus(documentFollowUp.getFollowupStatus().getId());
        dto.setSummary(documentFollowUp.getSummary());
        dto.setAssignedToUser(documentFollowUp.getAssignedTo().getName());
        dto.setAssignedByUser(documentFollowUp.getCreatedBy().getName());
        dto.setAssignedByUserNo(documentFollowUp.getCreatedBy().getId());
        dto.setAssignedAt(documentFollowUp.getCreatedAt().toString());
        dto.setDocFollowUpActivities(docFollowUpActivityDTOS);
        dto.setDueDate(documentFollowUp.getDueDate() != null ? documentFollowUp.getDueDate().toString(): null);
        dto.setDepartmentNo(documentFollowUp.getDepartment().getId());

        switch (lang) {
            case "dr": {
                dto.setFollowupStatusDesc(documentFollowUp.getFollowupStatus().getNameDr());
                dto.setAssignedByJob(documentFollowUp.getCreatedBy().getJob().getNameDr());
                dto.setAssignedByDepartment(documentFollowUp.getCreatedBy().getDepartment().getNameDr());
                dto.setAssignedByEntity(documentFollowUp.getCreatedBy().getEntity().getNameDr());
                dto.setAssignedToJob(documentFollowUp.getAssignedTo().getJob().getNameDr());
                dto.setAssignedToDepartment(documentFollowUp.getAssignedTo().getDepartment().getNameDr());
                dto.setAssignedToEntity(documentFollowUp.getAssignedTo().getEntity().getNameDr());
                dto.setDepartment(documentFollowUp.getDepartment().getNameDr());

                if(topDocFlActivity != null) {
                    dto.setFollowupStatus(topDocFlActivity.getFollowupStatus().getId());
                    dto.setFollowupStatusDesc(topDocFlActivity.getFollowupStatus().getNameDr());
                }
                break;
            }
            case "ps": {
                dto.setFollowupStatusDesc(documentFollowUp.getFollowupStatus().getNamePs());
                dto.setAssignedByJob(documentFollowUp.getCreatedBy().getJob().getNamePs());
                dto.setAssignedByDepartment(documentFollowUp.getCreatedBy().getDepartment().getNamePs());
                dto.setAssignedByEntity(documentFollowUp.getCreatedBy().getEntity().getNamePs());
                dto.setAssignedToJob(documentFollowUp.getAssignedTo().getJob().getNamePs());
                dto.setAssignedToDepartment(documentFollowUp.getAssignedTo().getDepartment().getNamePs());
                dto.setAssignedToEntity(documentFollowUp.getAssignedTo().getEntity().getNamePs());
                dto.setDepartment(documentFollowUp.getDepartment().getNamePs());

                if(topDocFlActivity != null) {
                    dto.setFollowupStatus(topDocFlActivity.getFollowupStatus().getId());
                    dto.setFollowupStatusDesc(topDocFlActivity.getFollowupStatus().getNameDr());
                }
                break;
            }
            default: {
                dto.setFollowupStatusDesc(documentFollowUp.getFollowupStatus().getNameEn());
                dto.setAssignedByJob(documentFollowUp.getCreatedBy().getJob().getNameEn());
                dto.setAssignedByDepartment(documentFollowUp.getCreatedBy().getDepartment().getNameEn());
                dto.setAssignedByEntity(documentFollowUp.getCreatedBy().getEntity().getNameEn());
                dto.setAssignedToJob(documentFollowUp.getAssignedTo().getJob().getNameEn());
                dto.setAssignedToDepartment(documentFollowUp.getAssignedTo().getDepartment().getNameEn());
                dto.setAssignedToEntity(documentFollowUp.getAssignedTo().getEntity().getNameEn());
                dto.setDepartment(documentFollowUp.getDepartment().getNameEn());

                if(topDocFlActivity != null) {
                    dto.setFollowupStatus(topDocFlActivity.getFollowupStatus().getId());
                    dto.setFollowupStatusDesc(topDocFlActivity.getFollowupStatus().getNameDr());
                }
                break;
            }
        }


        return dto;
    }

    public static DocFollowUpActivityDTO mapDocFollowupActivityDTO(DocFollowUpActivity activity, String lang) {
        DocFollowUpActivityDTO dto = new DocFollowUpActivityDTO();
        dto.setId(activity.getId());
        dto.setFileName(activity.getFileName());
        dto.setCreatedBy(activity.getCreatedBy().getUsername());
        dto.setCreatedByNo(activity.getCreatedBy().getId());
        dto.setSummary(activity.getSummary());
        dto.setCreatedAt(activity.getCreatedAt().toString());
        dto.setDocFollowUpId(activity.getDocumentFollowUp().getId());
        dto.setIsFinal(activity.getIsFinal());

        switch (lang) {
            case "dr": {
                dto.setDocumentFollowupStatus(activity.getFollowupStatus().getNameDr());
                dto.setDocumentFollowupType(activity.getDocumentFollowUpType().getNameDr());
                break;
            }

            case "ps": {
                dto.setDocumentFollowupStatus(activity.getFollowupStatus().getNamePs());
                dto.setDocumentFollowupType(activity.getDocumentFollowUpType().getNamePs());
                break;
            }
            default: {
                dto.setDocumentFollowupStatus(activity.getFollowupStatus().getNameEn());
                dto.setDocumentFollowupType(activity.getDocumentFollowUpType().getNameEn());
                break;
            }
        }

        dto.setDocumentFollowupStatusNo(activity.getFollowupStatus().getId());
        dto.setDocumentFollowupTypeNo(activity.getDocumentFollowUpType().getId());

        return dto;
    }
}
