package com.nsia.officems.edu_publication.profile_job;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems._admin.academicPosition.AcademicPosition;
import com.nsia.officems._admin.university.University;
import com.nsia.officems._admin.university_department.UniversityDepartment;
import com.nsia.officems._admin.university_faculty.UniversityFaculty;
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
@Entity(name = "ProfileJob")
@Table(name = "edu_teacher_job")
public class ProfileJob{  
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "edu_teacher_job_tbl_generator")
	@SequenceGenerator(name="edu_teacher_job_tbl_generator", sequenceName = "edu_teacher_job_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(name = "start_date")
	private String startDate;

    @Column(name = "status")
	private String status;

    @ManyToOne
    @JoinColumn(name="position_id", referencedColumnName = "id", nullable = false)
    private AcademicPosition position;

    @ManyToOne
    @JoinColumn(name="university_id", referencedColumnName = "id", nullable = false)
    private University university;

    @ManyToOne
    @JoinColumn(name="faculty_id", referencedColumnName = "id", nullable = false)
    private UniversityFaculty faculty;

    @ManyToOne
    @JoinColumn(name="department_id", referencedColumnName = "id", nullable = false)
    private UniversityDepartment department;

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
