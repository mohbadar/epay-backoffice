package com.nsia.officems.doc_mng.document.dto;

import java.util.List;

import com.nsia.officems.doc_mng.document.Document;
import com.nsia.officems.doc_mng.document.document_note_section.DocumentNoteSection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DocumentPrintDto {
    private String fontBoldUri;
    private String fontLightUri;
    private String fontRomanUri;
    private String content;
    private String template;
    private Long documentId;
    private Document document;
    private String documentDate;
    private String departmentHeader;
    private String departmentFooter;
    private String serverApi;
    private String createdAt;
    private List<DocumentNoteSection> noteSections;
    private String hukumPeshnehad;
    private String directorOrder;
}
