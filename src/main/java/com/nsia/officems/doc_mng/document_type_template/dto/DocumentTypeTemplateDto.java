package com.nsia.officems.doc_mng.document_type_template.dto;

import java.time.LocalDateTime;
import com.nsia.officems._identity.authentication.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author mohbadar
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTypeTemplateDto {
    private Long id;
    private String template;
    private Long documentTypeId;
    private Long entityId;
    private Long departmentId;
    private Long deletedBy;
    private LocalDateTime deletedAt;
    private User createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String header;
    private String footer;

}
