package com.nsia.officems.hr.profile.education;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems._admin.country.Country;
import com.nsia.officems._admin.education_level.EducationLevel;
import com.nsia.officems._infrastructure.base.BaseEntity;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Audited
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "ProfileEducation")
@Table(name = "hr_profile_education")
public class ProfileEducation extends BaseEntity {  
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hr_profile_education_tbl_generator")
	@SequenceGenerator(name="hr_profile_education_tbl_generator", sequenceName = "hr_profile_education_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date graduationDate;
    private String duration;
    private String fieldOfStudy;
    private String university;
    private String universityType;
    private Boolean insideWork;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "education_country", nullable = true, referencedColumnName = "id")
    private Country country;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "education_level", nullable = true, referencedColumnName = "id")
    private EducationLevel level;

    // @NotAudited
    // @ManyToOne
    // @JoinColumn(name = "profile_id", nullable = true, referencedColumnName = "id")
    // private Profile profile;

    // @NotAudited
    // @ManyToOne
    // @JoinColumn(name = "profile_job", nullable = true, referencedColumnName = "id")
    // private ProfileJob profileJob;


    
    
}
