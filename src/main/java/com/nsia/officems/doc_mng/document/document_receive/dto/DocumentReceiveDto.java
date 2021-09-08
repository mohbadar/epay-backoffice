package com.nsia.officems.doc_mng.document.document_receive.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DocumentReceiveDto {

    private long id;
    private String comment;
    private String decision;
    private String userId;
    private Long documentId;
    
}
