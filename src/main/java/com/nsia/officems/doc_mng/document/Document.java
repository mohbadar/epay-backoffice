package com.nsia.officems.doc_mng.document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems._admin.department.Department;
import com.nsia.officems._admin.document_category.DocumentCategory;
import com.nsia.officems._admin.issuing_authority.IssuingAuthority;
import com.nsia.officems._admin.province.Province;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.doc_mng.document.document_review.DocumentReview;
import com.nsia.officems.doc_mng.document_note_type.DocumentNoteType;
import com.nsia.officems.doc_mng.document_status.DocumentStatus;
import com.nsia.officems.doc_mng.document_type.DocumentType;
import com.nsia.officems.doc_mng.enumeration.DocumentPriorityType;
import com.nsia.officems.doc_mng.enumeration.DocumentSecurityLevel;
import com.nsia.officems.doc_mng.document.document_note_section.DocumentNoteSection;
import com.nsia.officems.doc_mng.document.document_receive.DocumentReceive;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
@Table(name = "doc_mng_document")
@Entity(name = "Document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_mng_document_tbl_generator")
    @SequenceGenerator(name = "doc_mng_document_tbl_generator", sequenceName = "doc_mng_document_tbl_seq", allocationSize = 1)
    private Long id;

    // 0: Document    1: Execution
    @Column
    private Long type;

    @Column
    private String documentNo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date documentDate;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "attachment")
    private String attachment;

    // DRAFT, FINAL : IT is used to identify. when the document is final, it will
    // not be editable by owner
    @Column
    private String status;

    @Column(name = "attachment_path")
    private String attachmentPath;

    // NORMAL, MEDIUM, URGENT
    @Column
    private String documentPriorityType;

    // NORMAL, SECRET, TOPSECRET;
    @Column
    private String documentSecurityLevel;

    @Column(columnDefinition = "boolean default false")
    private Boolean followup = false;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dueDate;

    // This is the date when document status is set is Executed - اجرا شد
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date completionDate;

    // This is the user to who the document is assigned to followup
    @ManyToOne
    @JoinColumn(name = "assigned_to", referencedColumnName = "id", nullable = true)
    private User assignedTo;

    // This is the department to who the document is assigned
    @OneToOne
    @JoinColumn(name = "referred_department_id", referencedColumnName = "id", nullable = true)
    private Department referredDepartment;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = false;

    @Column(name = "deleted_by")
    private Long deletedBy;

    @Column(name = "deleted_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "UTC")
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = false)
    private User createdBy;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "document_type_id", referencedColumnName = "id", nullable = false)
    private DocumentType documentType;

    @ManyToOne
    @JoinColumn(name = "document_status_id", referencedColumnName = "id", nullable = false)
    private DocumentStatus documentStatus;

    @ManyToOne
    @JoinColumn(name = "from_entity_id", referencedColumnName = "id", nullable = true)
    private Department fromEntity;

    @OneToOne
    @JoinColumn(name = "from_department_id", referencedColumnName = "id", nullable = true)
    private Department fromDepartment;

    @OneToOne
    @JoinColumn(name = "to_department_id", referencedColumnName = "id", nullable = true)
    private Department toDepartment;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cc_department_id", referencedColumnName = "id")
    private List<Department> ccDepartments;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "doc_mng_document_linked_doc", joinColumns = @JoinColumn(name = "document_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "linked_document_id", referencedColumnName = "id"))
    @JsonIgnore
    private List<Document> linkedDocuments;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id", referencedColumnName = "id")
    private List<DocumentReview> reviews;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "receive_id", referencedColumnName = "id")
    private List<DocumentReceive> receives;

    @Column
    private String scope;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date implementationEndDate;

    @ManyToOne
    @JoinColumn(name = "issuing_authority_id", referencedColumnName = "id")
    private IssuingAuthority issuingAuthority;

    @Column
    private String offerNo;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private DocumentCategory category;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "province_id", referencedColumnName = "id")
    private List<Province> guidanceProvinces;

    @Column
    private Boolean verbal;

    @Column
    private String directorInstruction;

    @Column
    private String maktobNo;

    @ManyToOne
    @JoinColumn(name = "note_type_id", referencedColumnName = "id", nullable = true)
    private DocumentNoteType noteType;

    @OneToMany(mappedBy="document")
    private List<DocumentNoteSection> noteSections;

    @OneToOne
    @JoinColumn(name = "main_document_id", referencedColumnName = "id", nullable = true)
    private Document mainDocument;

    @OneToOne
    @JoinColumn(name = "offeror_department_id", referencedColumnName = "id", nullable = true)
    private Department offerorDepartment;
}
