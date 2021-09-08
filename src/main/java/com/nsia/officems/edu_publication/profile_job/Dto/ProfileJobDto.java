package com.nsia.officems.edu_publication.profile_job.Dto;

import java.time.LocalDateTime;

import com.nsia.officems._admin.department.Department;
import com.nsia.officems._admin.university.University;
import com.nsia.officems._admin.university_department.UniversityDepartment;
import com.nsia.officems._admin.university_faculty.UniversityFaculty;
import com.nsia.officems._identity.authentication.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileJobDto {
    
    private Long id;
    private String startDate;
    private Long university;
    private Long faculty;
    private Long department;
    private Long profile;
    private Long position;
}
