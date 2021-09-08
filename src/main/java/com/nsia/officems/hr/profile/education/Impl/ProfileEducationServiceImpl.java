package com.nsia.officems.hr.profile.education.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nsia.officems._admin.country.CountryService;
import com.nsia.officems._admin.education_level.EducationLevelService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.hr.profile.education.ProfileEducationRepository;
import com.nsia.officems.hr.profile.education.ProfileEducationService;

import org.springframework.stereotype.Service;

@Service
public class ProfileEducationServiceImpl implements ProfileEducationService {

    // private ChecklistTitle titles = new ChecklistTitle();

    @Autowired
    private ProfileEducationRepository educationRepository;

    @Autowired
    private CountryService countryService;

    @Autowired
    private EducationLevelService educationLevelService;

    // @Autowired
    // private ProfileService profileService;

    // @Autowired
    // private ProfileChecklistService profileChecklistService;

    @Autowired
    UserService userService;

    // @Autowired
    // private ProfileJobService profileJobService;

    // @Override
    // public List<Education> findAll() {
    //     return educationRepository.findAll();
    // }

    // public List<RevisionDTO>  getEductionLog(Long id){
    //     Revisions<Integer, Education> indList = educationRepository.findRevisions(id);
    //     List<Revision<Integer, Education>> educations = indList.getContent();

    //     List<RevisionDTO> dtos= new ArrayList<>();
        
    //     for(Revision revision: educations){
    //             dtos.add(new RevisionDTO(revision.getEntity()));
    //     }

    //     return dtos;
    // }

    // public List<Education> findbyProfileJob_id(Long id) {
    //     return this.educationRepository.findByProfileJob_id(id);
    // }

    // @Override
    // public List<Education> findByProfile_id(Long id) {
    //     return educationRepository.findByProfile_idOrderByLevel_idDesc(id);
    // }

    // @Override
    // public Education findById(Long id) {
    //     Optional<Education> optionalObj = educationRepository.findById(id);
    //     if (optionalObj.isPresent())
    //         return optionalObj.get();
    //     return null;
    // }

    // @Override
    // public Education findbyEducationProfile(Long proId) {
    //     return this.educationRepository.findbyLatestEducationProfile(proId);
    // }

    // @Override
    // public Education create(EducationDto dto) {
    //     Education newEducation = new Education();
    //     Education education = EducationMapperDto.MapEducationDto(newEducation, dto, profileService, countryService,
    //             educationLevelService, profileJobService);
    //     education.setCreatedBy(userService.getLoggedInUser().getUsername());
    //     if (!education.equals(null)) {
    //         profileChecklistService.update(education.getProfile().getId(), titles.getEducation());
    //         return educationRepository.save(education);

    //     }
    //     return null;
    // }

    // public Boolean update(Long id, EducationDto dto) {
    //     Optional<Education> objection = educationRepository.findById(id);
    //     if (objection.isPresent()) {
    //         Education education = EducationMapperDto.MapEducationDto(objection.get(), dto, profileService,
    //                 countryService, educationLevelService, profileJobService);
    //         education.setUpdatedBy(userService.getLoggedInUser().getUsername());
    //         if (!education.equals(null)) {
    //             educationRepository.save(education);
    //             return true;
    //         }
    //         return false;
    //     }

    //     return false;
    // }

    // @Override
    // public Boolean delete(Long id) {
    //     Optional<Education> education = educationRepository.findById(id);

    //     if (education.isPresent()) {
    //         Education education2 = education.get();
    //         education2.setDeleted(true);
    //         education2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    //         educationRepository.save(education2);
    //         return true;
    //     }

    //     return false;
    // }

}
