package com.nsia.officems.doc_mng.document.dto;

import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.doc_mng.document.document_note_section.DocumentNoteSection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DocumentDto {
    private long id;
    private long type;
    private String documentNo;
    private String documentDate;
    private Time documentTime;

    private String title;
    private String content;

    private Long documentTypeId;
    private String documentTypeName;

    private String status;

    private String attachmentPath;

    private User deletedBy;
    private Date deletedAt;

    private User createdBy;
    private Date createdAt;
    private Date updatedAt;

    private Long fromDepartmentId;
    private Long fromDepartment;

    private Long toDepartmentId;
    private Long toDepartment;

    private List<Long> ccDepartmentsIds;

    private List<Long> reviewerIds;

    private List<DocumentNoteSection> noteSections;

    private String priorityType;
    private String securityLevel;

    private Long linkedDocument;

    private Long mainDocumentId;

    private String scope;
    private Long fromEntityId;

    // Hukum
    private String implementationEndDate;
    private Long issuingAuthority;
    private Long category;
    private String offerNo;
    private Long offerorDepartment;

    // Hidayat
    private List<Long>  guidanceProvinces;
    private Boolean verbal;
    private String directorInstruction;
    private String maktobNo;
    private Long noteType;

}
