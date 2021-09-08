package com.nsia.officems.edu_publication.profile_education;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems._admin.country.Country;
import com.nsia.officems._admin.education_level.EducationLevel;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.edu_publication.profile.TeacherProfile;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "Education")
@Table(name = "edu_teacher_education")
public class Education{  
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "edu_teacher_education_tbl_generator")
	@SequenceGenerator(name="edu_teacher_education_tbl_generator", sequenceName = "edu_teacher_education_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;
    private String startDate;
    private String graduationDate;
    private String duration;
    private String fieldOfStudy;
    private String university;
    private String universityType;

    @ManyToOne
    @JoinColumn(name = "education_country", nullable = true, referencedColumnName = "id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "education_level", nullable = true, referencedColumnName = "id")
    private EducationLevel level;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = true, referencedColumnName = "id")
    private TeacherProfile profile;
    
    
    @ManyToOne
    @JoinColumn(name="deleted_by", referencedColumnName = "id", nullable = true)
	private User deletedBy;

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
