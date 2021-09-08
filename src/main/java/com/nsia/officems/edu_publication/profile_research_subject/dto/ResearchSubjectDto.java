package com.nsia.officems.edu_publication.profile_research_subject.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResearchSubjectDto {

    private Long id;
    private Long type;
    private Long researchSubjectLanguage;
    private Long candidateToGrade;
    private Long status;
    private Long profile;
    private String title;
    private String description;
    private String RegisterDate;
    private String expireDate;
    private String comment;

    
}
