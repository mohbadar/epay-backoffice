package com.nsia.officems.doc_mng.document.document_comment.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DocumentCommentDto {

    private long id;
    private String comment;
    private Long documentId;
    
}
