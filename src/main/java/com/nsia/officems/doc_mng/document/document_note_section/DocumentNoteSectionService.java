package com.nsia.officems.doc_mng.document.document_note_section;

import java.io.File;
import java.util.List;

import com.nsia.officems.doc_mng.document.document_note_section.dto.DocumentNoteSectionDto;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentNoteSectionService {
    public DocumentNoteSection findById(Long id);
    public DocumentNoteSection save(DocumentNoteSection documentNoteSection);
    public List<DocumentNoteSection> saveAll(List<DocumentNoteSection> documentNoteSections);
    public DocumentNoteSection create(DocumentNoteSection documentNoteSection);

    // public List<DocumentNoteSection> findByDocumentId(Long id);
    // public DocumentNoteSection create(DocumentNoteSectionDto documentNoteSectionDto);
    // public Boolean update(Long id, String data, MultipartFile file);

    // public Boolean delete(Long id);
}
