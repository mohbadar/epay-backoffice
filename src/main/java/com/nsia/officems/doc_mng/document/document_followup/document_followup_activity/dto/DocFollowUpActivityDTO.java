package com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocFollowUpActivityDTO {

    private Long id;
    private Long docFollowUpId;
    private String summary;
    private String fileName;
    private String documentFollowupType;
    private Long documentFollowupTypeNo;
    private String documentFollowupStatus;
    private Long documentFollowupStatusNo;
    private String createdBy;
    private Long createdByNo;
    private String createdAt;
    private Boolean isFinal;
}
