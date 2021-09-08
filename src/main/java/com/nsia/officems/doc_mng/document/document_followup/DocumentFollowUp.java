package com.nsia.officems.doc_mng.document.document_followup;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems._admin.department.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.doc_mng.document.Document;
import com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.DocFollowUpActivity;
import com.nsia.officems.doc_mng.document_followup_status.DocumentFollowUpStatus;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doc_mng_document_follow_up")
@Entity(name = "DocumentFollowUp")
@Where(clause = "deleted is not true")
public class DocumentFollowUp {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_follow_up_tbl_generator")
    @SequenceGenerator(name = "document_follow_up_tbl_generator", sequenceName = "document_follow_up_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Column(name = "due_date")
    LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "followup_status_id", nullable = true, referencedColumnName = "id")
    private DocumentFollowUpStatus followupStatus;

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = true, referencedColumnName = "id")
    private Document document;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = true, referencedColumnName = "id")
    private Department department;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = false;
    
    @Column(name = "deleted_by")
	private Long deletedBy;

	@Column(name = "deleted_at")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "UTC")
	private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name="created_by", referencedColumnName = "id", nullable = false)
	private User createdBy;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "assigned_to", referencedColumnName = "id", nullable = false)
   	private User assignedTo;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documentFollowUp")
    private Collection<DocFollowUpActivity> docFollowUpActivityCollection;

    public DocumentFollowUp(Long id, String summary, User createdBy, LocalDateTime createdAt) {
        this.id = id;
        this.summary = summary;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
}
