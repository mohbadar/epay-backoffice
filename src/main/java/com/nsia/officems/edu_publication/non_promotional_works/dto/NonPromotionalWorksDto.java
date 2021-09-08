package com.nsia.officems.edu_publication.non_promotional_works.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NonPromotionalWorksDto {

    private Long id;
    private Long status;
    private Long profile;
    private Long researchSubject;
    private String title;
    private String field;
    private String semester;
    private String description;
    private String comment;
    private String publishDate;

    
}
