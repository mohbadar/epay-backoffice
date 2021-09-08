package com.nsia.officems.edu_publication.profile_education.Impl;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._admin.country.CountryService;
import com.nsia.officems._admin.education_level.EducationLevelService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.edu_publication.profile.TeacherProfileService;
import com.nsia.officems.edu_publication.profile_education.Education;
import com.nsia.officems.edu_publication.profile_education.EducationRepository;
import com.nsia.officems.edu_publication.profile_education.EducationService;
import com.nsia.officems.edu_publication.profile_education.Dto.EducationDto;
import com.nsia.officems.edu_publication.profile_education.Dto.EducationMapperDto;

import org.springframework.stereotype.Service;

@Service
public class EducationServiceImpl implements EducationService{

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private CountryService countryService;

    @Autowired
    private EducationLevelService educationLevelService;

    @Autowired
    private TeacherProfileService profileService;

    @Autowired
    UserService userService;

    @Override
    public List<Education> findAll() {
        return educationRepository.findAll();
    }

    @Override
    public List<Education> findByProfile_id(Long id) {
        return educationRepository.findByProfile_idOrderByLevel_idDesc(id);
    }

    @Override
    public Education findById(Long id) {
        Optional<Education> optionalObj = educationRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Education create(EducationDto dto) {
        Education newEducation = new Education();
        Education education = EducationMapperDto.MapEducationDto(newEducation, dto, profileService, countryService, educationLevelService);

        if (!education.equals(null)) {
            education.setCreatedAt(LocalDateTime.now());
            education.setCreatedBy(userService.getLoggedInUser());
            return educationRepository.save(education);
        }
        return null;
    }

    public Boolean update(Long id, EducationDto dto) {
        Optional<Education> objection = educationRepository.findById(id);
        if (objection.isPresent()) {
            Education education = EducationMapperDto.MapEducationDto(objection.get(), dto, profileService, countryService, educationLevelService);
            if(!education.equals(null))
            {
                education.setUpdatedAt(LocalDateTime.now());
                
                educationRepository.save(education);
                return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Education> education = educationRepository.findById(id);

        if (education.isPresent()) {
            Education education2 = education.get();
            education2.setDeletedBy(userService.getLoggedInUser());
            education2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            educationRepository.save(education2);
            return true;
        }

        return false;
    }
    
}
