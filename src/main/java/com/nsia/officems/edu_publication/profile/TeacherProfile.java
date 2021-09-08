package com.nsia.officems.edu_publication.profile;

import java.time.LocalDateTime;
import java.util.List;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems._admin.academicGrade.AcademicGrade;
import com.nsia.officems._admin.country.Country;
import com.nsia.officems._admin.district.District;
import com.nsia.officems._admin.gender.Gender;
import com.nsia.officems._admin.province.Province;
import com.nsia.officems._admin.university.University;
import com.nsia.officems._identity.authentication.user.User;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "edu_teacher_profile")
@Entity(name = "TeacherProfile")
public class TeacherProfile {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "edu_teacher_profile_tbl_generator")
	@SequenceGenerator(name="edu_teacher_profile_tbl_generator", sequenceName = "edu_teacher_profile_tbl_generator_tbl_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "father_name")
    private String fatherName;

    @NotNull
    @Column(name = "grand_father_name")
    private String grandFatherName;;

    @NotNull
    @Column(name = "phone_no")
    private String phonNo;

    @NotNull
    @Column(name = "email")
    private String email;

    @Column(name = "photo")
    private String photo;

    @Column(name = "dob")
    private String dob;

    @Column(name = "dob_gregorian")
    private String dobGregorian;

    @Column(name = "tazkira_number")
    private String tazkiraNumber;

    @Column(name = "tazkira_tog")
    private String tazkiraTog;

    @Column(name = "tazkira_register")
    private String tazkiraRegister;

    @Column(name = "tazkira_page")
    private String tazkiraPage;

    @Column(name = "tazkira_date")
    private String tazkiraDate;

    @Column(name = "tazkira_place")
    private String tazkiraPlace;

    @Column(name = "enid")
    private String enid;

    @ManyToOne
    @JoinColumn(name="gender_id", referencedColumnName = "id", nullable = false)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name="birth_country_id", referencedColumnName = "id", nullable = false)
    private Country birthCountry;

    @ManyToOne
    @JoinColumn(name="birth_province_id", referencedColumnName = "id", nullable = false)
    private Province birthProvince;
    @ManyToOne
    @JoinColumn(name="birth_district_id", referencedColumnName = "id", nullable = false)
    private District birthDistrict;

    @ManyToOne
    @JoinColumn(name="original_country_id", referencedColumnName = "id", nullable = false)
    private Country originalCountry;

    @ManyToOne
    @JoinColumn(name="original_province_id", referencedColumnName = "id", nullable = false)
    private Province originalProvince;
    @ManyToOne
    @JoinColumn(name="original_district_id", referencedColumnName = "id", nullable = false)
    private District originalDistrict;

    @ManyToOne
    @JoinColumn(name="current_country_id", referencedColumnName = "id", nullable = false)
    private Country currentCountry;

    @ManyToOne
    @JoinColumn(name="current_province_id", referencedColumnName = "id", nullable = false)
    private Province currentProvince;
    @ManyToOne
    @JoinColumn(name="current_district_id", referencedColumnName = "id", nullable = false)
    private District currentDistrict;


    @ManyToOne
    @JoinColumn(name="academic_grade_id", referencedColumnName = "id", nullable = false)
    private AcademicGrade academicGrade;
    
    @ManyToOne
    @JoinColumn(name="current_university_id", referencedColumnName = "id", nullable = false)
    private University currentUniversity;

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
