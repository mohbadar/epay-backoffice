package com.nsia.officems.edu_publication.non_promotional_works;
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
import com.nsia.officems._admin.academicGrade.AcademicGrade;
import com.nsia.officems._admin.article_type.ArticleType;
import com.nsia.officems._admin.language.Language;
import com.nsia.officems._admin.publicationSource.PublicationSource;
import com.nsia.officems._admin.publication_type.PublicationType;
import com.nsia.officems._admin.researchSubjectLanguage.ResearchSubjectLanguage;
import com.nsia.officems._admin.reviewStatus.ReviewStatus;
import com.nsia.officems._infrastructure.base.BaseEntity;
import com.nsia.officems.edu_publication.profile.TeacherProfile;
import com.nsia.officems.edu_publication.profile_research_subject.ResearchSubject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "NonPromotionalWorks")
@Table(name = "edu_non_promotional_works")
public class NonPromotionalWorks extends BaseEntity{
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "non_promotional_works_tbl_generator")
	@SequenceGenerator(name="non_promotional_works_tbl_generator", sequenceName = "non_promotional_works_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "semester")
    private String semester;

    @Column(name = "field")
    private String field;

    @Column(name = "comment")
    private String comment;
  
    @Column(name = "table_of_content")
    private String tableOfContent;

    @Column(name = "commission_decision")
    private String commissionDecision;

    @Column(name = "publish_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "UTC")
    @JsonIgnore
    private LocalDateTime publishDate;

    @Column(name = "decision_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "UTC")
    @JsonIgnore
    private LocalDateTime decisionDate;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = true, referencedColumnName = "id")
    private ReviewStatus status;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = true, referencedColumnName = "id")
    private TeacherProfile profile;

    @ManyToOne
    @JoinColumn(name = "research_subject_id", nullable = true, referencedColumnName = "id")
    private ResearchSubject researchSubject;
    
}
