package com.nsia.officems.doc_mng.document.document_followup.dto;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.dto.DocFollowUpActivityDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DocumentFollowUpDto {
    private Long id;
    private String summary;
    private Long followupStatus;
    private Long documentId;
    private Long assignedTo;

    // Following are for presentation purpose not to be saved from here
    private List<DocFollowUpActivityDTO> docFollowUpActivities;
    private String assignedToUser;
    private String followupStatusDesc;
    private String assignedAt;
    private String assignedByUser;
    private Long assignedByUserNo;
    private String assignedByJob;
    private String assignedByDepartment;
    private String assignedByEntity;

    private String assignedToJob;
    private String assignedToDepartment;
    private String assignedToEntity;

    private String dueDate;
    private String department;
    private Long departmentNo;

}
