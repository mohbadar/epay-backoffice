package com.nsia.officems.doc_mng.document.document_receive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

import com.nsia.officems._admin.department.Department;

import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.doc_mng.document.Document;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doc_mng_document_receive")
@Entity(name = "DocumentReceive")
public class DocumentReceive {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_mng_in_doc_exe_receive_tbl_generator")
    @SequenceGenerator(name = "doc_mng_in_doc_exe_receive_tbl_generator", sequenceName = "doc_mng_in_doc_exe_receive_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    // RECEIVED, REJECTED, PENDING
    @Column
    private String decision;

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

    @OneToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "document_id", referencedColumnName = "id", nullable = true)
    @JsonIgnore
    private Document document;

    @ManyToOne
    @JoinColumn(name = "receiver", referencedColumnName = "id", nullable = true)
    private User receiver;

    @Column(name = "received_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "UTC")
    private LocalDateTime receivedAt;
}
