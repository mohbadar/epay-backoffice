package com.nsia.officems.doc_mng.document.document_review.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DocumentReviewDto {

    private long id;
    private String comment;
    private String decision;
    private String userId;
    private Long documentId;
    
}
