package com.nsia.officems.edu_publication.profile_publication.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfilePublicationDto {

    private Long id;
    private String title;
    private String publishDate;
    private String articleLink;
    private Long articleType;
    private Long publicationSource;
    private String impactFactor;
    private String journal;
    private Long profile;
    
}
