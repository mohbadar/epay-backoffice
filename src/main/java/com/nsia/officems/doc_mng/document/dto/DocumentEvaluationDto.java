package com.nsia.officems.doc_mng.document.dto;

import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.nsia.officems._identity.authentication.user.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DocumentEvaluationDto {
    private Long documentId;
    private Boolean followup;
    private String dueDate;
    private Long referredDepartmentId;
    private List<Long> ccDepartmentsIds;
    private String content;
}
