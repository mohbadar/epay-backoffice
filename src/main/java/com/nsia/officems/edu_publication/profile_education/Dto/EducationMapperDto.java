package com.nsia.officems.edu_publication.profile_education.Dto;

import com.nsia.officems._admin.country.CountryService;
import com.nsia.officems._admin.education_level.EducationLevelService;
import com.nsia.officems.edu_publication.profile.TeacherProfileService;
import com.nsia.officems.edu_publication.profile_education.Education;

public class EducationMapperDto {

    public static Education MapEducationDto(Education education, EducationDto dto,
     TeacherProfileService profileService, CountryService countryService, EducationLevelService educationLevelService){ 

        try{
            education.setStartDate(dto.getStartDate() == null? null: dto.getStartDate());
            education.setDuration(dto.getDuration() == null? null: dto.getDuration());
            education.setGraduationDate(dto.getGraduationDate() == null? null: dto.getGraduationDate());
            education.setUniversity(dto.getUniversity() == null? null: dto.getUniversity());
            education.setFieldOfStudy(dto.getFieldOfStudy() == null? null: dto.getFieldOfStudy());
            education.setUniversityType(dto.getUniversityType() == null? null: dto.getUniversityType());
            education.setCountry(dto.getCountry() == null? null: countryService.findById(dto.getCountry()));
            education.setLevel(dto.getLevel() == null? null: educationLevelService.findById(dto.getLevel()));
            education.setProfile(dto.getProfile() == null? null: profileService.findById(dto.getProfile()));

            return education;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
    
}
