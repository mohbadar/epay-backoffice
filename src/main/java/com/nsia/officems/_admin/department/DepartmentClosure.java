package com.nsia.officems._admin.department;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems._infrastructure.base.BaseEntity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "DepartmentClosure")
@Table(name = "department_closure")
public class DepartmentClosure { 
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_closure_tbl_generator")
	@SequenceGenerator(name="department_closure_tbl_generator", sequenceName = "department_closure_tbl_seq", allocationSize = 1)
	private Long id;
	
	@Column
	private Long depth;

	@OneToOne
    @JoinColumn(name = "ancestor_id", referencedColumnName = "id", nullable = false)
    private Department ancestor;

	@OneToOne
    @JoinColumn(name = "descendant_id", referencedColumnName = "id", nullable = false)
    private Department descendant;
}
