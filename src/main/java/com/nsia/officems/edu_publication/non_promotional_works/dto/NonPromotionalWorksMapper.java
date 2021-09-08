package com.nsia.officems.edu_publication.non_promotional_works.dto;
import com.nsia.officems._admin.reviewStatus.ReviewStatusService;
import com.nsia.officems._util.DateTimeUtil;
import com.nsia.officems.edu_publication.non_promotional_works.NonPromotionalWorks;
import com.nsia.officems.edu_publication.profile.TeacherProfileService;
import com.nsia.officems.edu_publication.profile_research_subject.ResearchSubjectService;

public class NonPromotionalWorksMapper {
    public static NonPromotionalWorks mapNonPromotionalWorksToDto(NonPromotionalWorks nonPromotionalWork, NonPromotionalWorksDto dto,
     TeacherProfileService profileService, ReviewStatusService reviewStatusService, ResearchSubjectService researchSubjectService , DateTimeUtil dateTimeUtil){ 

        try{
            if(nonPromotionalWork != null){
                nonPromotionalWork.setId(dto.getId());
            }
            nonPromotionalWork.setActive(true);
            nonPromotionalWork.setTitle(dto.getTitle() == null? null: dto.getTitle());
            nonPromotionalWork.setSemester(dto.getTitle() == null? null: dto.getSemester());
            nonPromotionalWork.setField(dto.getTitle() == null? null: dto.getField());
            nonPromotionalWork.setDescription(dto.getDescription() == null? null: dto.getDescription());
            nonPromotionalWork.setProfile(dto.getProfile() == null? null: profileService.findById(dto.getProfile()));
            nonPromotionalWork.setStatus(dto.getStatus() == null? null: reviewStatusService.findById(dto.getStatus()));
            nonPromotionalWork.setResearchSubject(dto.getResearchSubject()==null?null: researchSubjectService.findById(dto.getResearchSubject()));
            nonPromotionalWork.setPublishDate(dateTimeUtil.convertDateStringToLocalDateTime(dto.getPublishDate()));
            return nonPromotionalWork;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
    
}
