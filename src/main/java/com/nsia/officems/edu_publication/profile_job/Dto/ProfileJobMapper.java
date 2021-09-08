package com.nsia.officems.edu_publication.profile_job.Dto;

import com.nsia.officems._admin.academicPosition.AcademicPositionService;
import com.nsia.officems._admin.country.CountryService;
import com.nsia.officems._admin.university.UniversityService;
import com.nsia.officems._admin.university_department.UniversityDepartmentService;
import com.nsia.officems._admin.university_faculty.UniversityFacultyService;
import com.nsia.officems.edu_publication.profile.TeacherProfileService;
import com.nsia.officems.edu_publication.profile_job.ProfileJob;

public class ProfileJobMapper {

    public static ProfileJob MapProfileJobDto(ProfileJob profileJob, ProfileJobDto dto,
     TeacherProfileService profileService, UniversityService universityService, UniversityFacultyService facultyService, UniversityDepartmentService departmentService, AcademicPositionService academicPositionService){ 

        try{
            profileJob.setStartDate(dto.getStartDate() == null? null: dto.getStartDate());
            profileJob.setUniversity(dto.getUniversity() == null? null: universityService.findById(dto.getUniversity()));
            profileJob.setFaculty(dto.getFaculty() == null? null: facultyService.findById(dto.getFaculty()));
            profileJob.setDepartment(dto.getDepartment() == null? null: departmentService.findById(dto.getDepartment()));
            profileJob.setProfile(dto.getProfile() == null? null: profileService.findById(dto.getProfile()));
            profileJob.setPosition(dto.getPosition() == null? null: academicPositionService.findById(dto.getPosition()));

            return profileJob;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
    
}
