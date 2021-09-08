package com.nsia.officems._admin.university_department;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nsia.officems._admin.university.University;
import com.nsia.officems._admin.university_faculty.UniversityFaculty;
import com.nsia.officems._infrastructure.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "UniversityDepartment")
@Table(name = "edu_university_department")
public class UniversityDepartment extends BaseEntity{
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "edu_university_department_tbl_generator")
	@SequenceGenerator(name="edu_university_department_tbl_generator", sequenceName = "edu_university_department_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;
	@Column
	private String namePs;
	@Column
	private String nameDr;
	@Column
	private String nameEn;

	@ManyToOne
	@JoinColumn(name = "faculty_id", nullable = false , referencedColumnName = "id")
	private UniversityFaculty faculty;

    
}
