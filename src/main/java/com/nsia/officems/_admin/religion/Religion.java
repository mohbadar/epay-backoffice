package com.nsia.officems._admin.religion;

import com.nsia.officems._infrastructure.base.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Entity(name = "Religion")
@Table(name = "religion")
public class Religion extends BaseEntity{
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nationality_tbl_generator")
	@SequenceGenerator(name="nationality_tbl_generator", sequenceName = "nationality_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
	private String namePs;

	@Column
	private String nameDr;
	
	@Column
	private String nameEn;
    
}
