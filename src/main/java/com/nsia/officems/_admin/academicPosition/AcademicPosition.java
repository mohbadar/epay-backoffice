package com.nsia.officems._admin.academicPosition;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nsia.officems._infrastructure.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "AcademicPosition")
@Table(name = "edu_academic_position")
public class AcademicPosition extends BaseEntity{
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "edu_academic_position_tbl_generator")
	@SequenceGenerator(name="edu_academic_position_tbl_generator", sequenceName = "edu_academic_position_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;
	@Column
	private String code;
	@Column
	private String namePs;
	@Column
	private String nameDr;
	@Column
	private String nameEn;
    
}
