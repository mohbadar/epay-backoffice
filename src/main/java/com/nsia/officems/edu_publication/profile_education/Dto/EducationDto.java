package com.nsia.officems.edu_publication.profile_education.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EducationDto {
    
    private Long id;
    private String startDate;
    private String graduationDate;
    private String duration;
    private String fieldOfStudy;
    private String university;
    private String universityType;
    private Long country;
    private Long level;
    private Long profile;
}
