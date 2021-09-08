package com.nsia.officems.edu_publication.profile_research_subject;
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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "ResearchSubject")
@Table(name = "edu_research_subject")
public class ResearchSubject extends BaseEntity{
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "research_subject_tbl_generator")
	@SequenceGenerator(name="research_subject_tbl_generator", sequenceName = "research_subject_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "comment")
    private String comment;

    @Column(name = "commission_decision")
    private String commissionDecision;
    
    @Column(name = "decision_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "UTC")
    @JsonIgnore
    private LocalDateTime decisionDate;
    
    @Column(name = "expire_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "UTC")
    @JsonIgnore
    private LocalDateTime expireDate;

    @Column(name = "suggestion")
    private String suggestion;

    @Column(name = "subjectContent")
    private String subjectContent;

    @Column(name = "certificate")
    private String certificate;

    @Column(name = "administrative_documents")
    private String administrativeDocuments;

    @Column(name = "evaluation_form")
    private String evaluationForm;

    @Column(name = "subject_maktob")
    private String subjectMaktob;

    @Column(name = "completed_subject")
    private String completedSubject;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = true, referencedColumnName = "id")
    private ReviewStatus status;

    @ManyToOne
    @JoinColumn(name = "complement_status_id", nullable = true, referencedColumnName = "id")
    private ReviewStatus ComplementStatus;

    @ManyToOne
    @JoinColumn(name = "grade_id", nullable = true, referencedColumnName = "id")
    private AcademicGrade candidateToGrade;

    @ManyToOne
    @JoinColumn(name = "publication_type_id", nullable = true, referencedColumnName = "id")
    private PublicationType type;

    @ManyToOne
    @JoinColumn(name = "subject_language_id", nullable = true, referencedColumnName = "id")
    private ResearchSubjectLanguage researchSubjectLanguage;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = true, referencedColumnName = "id")
    private TeacherProfile profile;
    
}
