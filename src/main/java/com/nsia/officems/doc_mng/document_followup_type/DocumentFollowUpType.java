package com.nsia.officems.doc_mng.document_followup_type;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._infrastructure.base.BaseEntity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "DocumentFollowUpType")
@Table(name = "doc_mng_document_followup_type")
public class DocumentFollowUpType {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_followup_type_tbl_generator")
	@SequenceGenerator(name="document_followup_type_tbl_generator", sequenceName = "document_followup_type_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
	private String code;

	@Column
	private String namePs;
	@Column
	private String nameDr;
	@Column
	private String nameEn;

	@Column(name = "active", length = 1, nullable = false)
    private boolean active;

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
}
