package com.nsia.officems.edu_publication.profile.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeacherProfileDto {

    private long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String grandFatherName;
    private String dob;
    private String dobGregorian;
    private Long gender;
    private String phonNo;
    private String email;
    private String photo;

    private String tazkiraNumber;

    private String tazkiraTog;

    private String tazkiraRegister;

    private String tazkiraPage;

    private String tazkiraDate;

    private String tazkiraPlace;

    private String enid;

    private Long birthCountry;
    private Long birthProvince;
    private Long birthDistrict;
    private Long originalCountry;
    private Long originalDistrict;
    private Long originalProvince;
    private Long currentCountry;
    private Long currentProvince;
    private Long currentDistrict;
    private Long currentUniversity;
    private Long academicGrade;
    private Long createdBy;
    private LocalDateTime createdAt;


  
    
}
