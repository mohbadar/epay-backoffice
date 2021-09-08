package com.nsia.officems.doc_mng.document_type_template;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems._admin.department.Department;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.doc_mng.document_type.DocumentType;

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
@Table(name = "doc_mng_document_type_template")
@Entity(name = "DocumentTypeTemplate")
public class DocumentTypeTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_mng_document_type_template_tbl_generator")
    @SequenceGenerator(name = "doc_mng_document_type_template_tbl_generator", sequenceName = "doc_mng_document_type_template_tbl_seq", allocationSize = 1)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String template;

    @ManyToOne
    @JoinColumn(name = "document_type_id", referencedColumnName = "id", nullable = false)
    private DocumentType documentType;

    @OneToOne
    @JoinColumn(name = "entity_id", referencedColumnName = "id", nullable = false)
    private Department entity;

    @OneToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
    private Department department;

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

}
