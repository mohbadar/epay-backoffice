package com.nsia.officems._admin.ethnic;

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
@Entity(name = "Ethnic")
@Table(name = "ethnic")
public class Ethnic extends BaseEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ethnic_tbl_generator")
	@SequenceGenerator(name="ethnic_tbl_generator", sequenceName = "ethnic_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
	private String namePs;

	@Column
	private String nameDr;
	
	@Column
	private String nameEn;
    
}
