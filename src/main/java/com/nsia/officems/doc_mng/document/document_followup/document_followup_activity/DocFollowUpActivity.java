package com.nsia.officems.doc_mng.document.document_followup.document_followup_activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.doc_mng.document.document_followup.DocumentFollowUp;
import com.nsia.officems.doc_mng.document_followup_status.DocumentFollowUpStatus;
import com.nsia.officems.doc_mng.document_followup_type.DocumentFollowUpType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doc_mng_doc_follow_up_activity")
@Entity(name = "DocFollowUpActivity")
@Where(clause = "deleted is not true")
public class DocFollowUpActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_follow_up_act_tbl_generator")
    @SequenceGenerator(name = "doc_follow_up_act_tbl_generator", sequenceName = "doc_follow_up_act_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    private String fileName;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = true, referencedColumnName = "id")
    private DocumentFollowUpType documentFollowUpType;

    @ManyToOne
    @JoinColumn(name = "followup_status_id", nullable = true, referencedColumnName = "id")
    private DocumentFollowUpStatus followupStatus;

    @ManyToOne
    @JoinColumn(name = "document_followup_id", nullable = true, referencedColumnName = "id")
    private DocumentFollowUp documentFollowUp;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean isFinal;

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

}
