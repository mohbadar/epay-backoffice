package com.nsia.officems._admin.researchSubjectLanguage;
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
@Entity(name = "ResearchSubjectLanguage")
@Table(name = "edu_research_subject_language")
public class ResearchSubjectLanguage extends BaseEntity{
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "research_subject_language_tbl_generator")
	@SequenceGenerator(name="research_subject_language_tbl_generator", sequenceName = "research_subject_language_tbl_seq", allocationSize = 1)
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
