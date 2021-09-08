package com.nsia.officems.edu_publication.profile_publication;
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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems._admin.article_type.ArticleType;
import com.nsia.officems._admin.publicationSource.PublicationSource;
import com.nsia.officems._admin.publication_type.PublicationType;
import com.nsia.officems._admin.reviewStatus.ReviewStatus;
import com.nsia.officems._infrastructure.base.BaseEntity;
import com.nsia.officems.edu_publication.profile.TeacherProfile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "ProfilePublication")
@Table(name = "edu_teacher_publication")
public class ProfilePublication extends BaseEntity{
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publication_tbl_generator")
	@SequenceGenerator(name="publication_tbl_generator", sequenceName = "publication_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;

    private String title;
    private String journal;
    private String articleLink;
    private String impactFactor;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "UTC")
    @JsonIgnore
    private LocalDateTime publishDate;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = true, referencedColumnName = "id")
    private ReviewStatus status;
    
    @ManyToOne
    @JoinColumn(name = "publication_source_id", nullable = true, referencedColumnName = "id")
    private PublicationSource publicationSource;

    @ManyToOne
    @JoinColumn(name = "article_type_id", nullable = true, referencedColumnName = "id")
    private ArticleType articleType;
    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = true, referencedColumnName = "id")
    private TeacherProfile profile;
    
}
