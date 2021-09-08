package com.nsia.officems.edu_publication.profile_research_subject.dto;

import com.nsia.officems._admin.academicGrade.AcademicGradeService;
import com.nsia.officems._admin.publication_type.PublicationTypeService;
import com.nsia.officems._admin.researchSubjectLanguage.ResearchSubjectLanguageService;
import com.nsia.officems._admin.reviewStatus.ReviewStatusService;
import com.nsia.officems.edu_publication.profile.TeacherProfileService;
import com.nsia.officems.edu_publication.profile_research_subject.ResearchSubject;

public class ResearchSubjectMapper {
    public static ResearchSubject mapResearchSubjectToDto(ResearchSubject researchSubject, ResearchSubjectDto dto,
     TeacherProfileService profileService, PublicationTypeService researchSubjectTypeService, ResearchSubjectLanguageService researchSubjectLanguageService, AcademicGradeService academicGradeService, ReviewStatusService reviewStatusService){ 

        try{
            if(researchSubject != null){
                researchSubject.setId(dto.getId());
            }
            researchSubject.setActive(true);
            researchSubject.setType(dto.getType() == null? null: researchSubjectTypeService.findById(dto.getType()));
            researchSubject.setTitle(dto.getTitle() == null? null: dto.getTitle());
            researchSubject.setDescription(dto.getDescription() == null? null: dto.getDescription());
            researchSubject.setProfile(dto.getProfile() == null? null: profileService.findById(dto.getProfile()));
            researchSubject.setStatus(dto.getStatus() == null? null: reviewStatusService.findById(dto.getStatus()));
            researchSubject.setResearchSubjectLanguage(dto.getResearchSubjectLanguage()== null? null: researchSubjectLanguageService.findById(dto.getResearchSubjectLanguage()));
            researchSubject.setCandidateToGrade(dto.getCandidateToGrade()== null? null: academicGradeService.findById(dto.getCandidateToGrade()));
            return researchSubject;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
    
}
