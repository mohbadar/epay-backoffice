package com.nsia.officems.doc_mng.document.document_attachment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.doc_mng.document.Document;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doc_mng_document_attachment")
@Entity(name = "DocumentAttachment")
public class DocumentAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_mng_document_attachment_tbl_generator")
    @SequenceGenerator(name = "doc_mng_document_attachment_tbl_generator", sequenceName = "doc_mng_document_attachment_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    private String fileName;

    @Column(columnDefinition = "varchar(800)")
    private String attachmentName;

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = true, referencedColumnName = "id")
    private Document document;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = false;
}
