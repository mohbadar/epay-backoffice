package com.nsia.officems.doc_mng.document.document_comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

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
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.doc_mng.document.Document;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doc_mng_document_comment")
@Entity(name = "DocumentComment")
public class DocumentComment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_mng_in_doc_comment_tbl_generator")
    @SequenceGenerator(name = "doc_mng_in_doc_comment_tbl_generator", sequenceName = "doc_mng_in_doc_comment_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    private String comment;

    private String fileName;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

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

    @ManyToOne
    @JoinColumn(name="document_id", referencedColumnName = "id", nullable = false)
	private Document document;

    public DocumentComment(Long id, String comment, Date date, User createdBy, LocalDateTime createdAt) {
        this.id = id;
        this.comment = comment;
        this.date = date;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
}
