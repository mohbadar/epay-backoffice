package com.nsia.officems.edu_publication.profile_publication.dto;

import com.nsia.officems._admin.article_type.ArticleTypeService;
import com.nsia.officems._admin.publicationSource.PublicationSourceService;
import com.nsia.officems._admin.publication_type.PublicationTypeService;
import com.nsia.officems._util.DateTimeUtil;
import com.nsia.officems.edu_publication.profile.TeacherProfileService;
import com.nsia.officems.edu_publication.profile_publication.ProfilePublication;

public class ProfilePublicationMapperDto {
    public static ProfilePublication MapPublicationDto(ProfilePublication publication, ProfilePublicationDto dto,
     TeacherProfileService profileService, PublicationTypeService publicationTypeService, PublicationSourceService publicationSourceService, ArticleTypeService articleTypeService, DateTimeUtil dateTimeUtil){ 

        try{
            publication.setActive(true);
            publication.setTitle(dto.getTitle() == null? null: dto.getTitle());
            publication.setJournal(dto.getJournal() == null? null: dto.getJournal());
            publication.setArticleLink(dto.getArticleLink() == null? null: dto.getArticleLink());
            publication.setImpactFactor(dto.getImpactFactor() == null? null: dto.getImpactFactor());
            publication.setPublishDate(dto.getPublishDate() == null? null : dateTimeUtil.convertDateStringToLocalDateTime(dto.getPublishDate()));
            publication.setProfile(dto.getProfile() == null? null: profileService.findById(dto.getProfile()));
            publication.setPublicationSource(dto.getPublicationSource()== null? null: publicationSourceService.findById(dto.getPublicationSource()));;
            publication.setArticleType(dto.getArticleType()== null? null: articleTypeService.findById(dto.getArticleType()));
            return publication;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping:: " + e.getMessage());
            return null;
        }
    }
    
}
