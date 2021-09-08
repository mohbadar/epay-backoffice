package com.nsia.officems.doc_mng.document.document_note_section.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DocumentNoteSectionDto {
    private Long id;
	private String title;
    @NotNull
	private String content;
    private Long orderCol;

    private Long documentId;
    
}
