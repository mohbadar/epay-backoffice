package com.nsia.officems.doc_mng.document.document_comment;

import java.io.File;
import java.util.List;

import com.nsia.officems.doc_mng.document.document_comment.dto.DocumentCommentDto;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentCommentService {
    public DocumentComment findById(Long id);
    public List<DocumentComment> findByDocumentId(Long id);
    // public DocumentComment create(String data, MultipartFile file);
    public DocumentComment save(DocumentComment documentComment);
    public DocumentComment create(DocumentComment documentComment);
    public DocumentComment create(DocumentCommentDto documentCommentDto);
    public Boolean update(Long id, String data, MultipartFile file);
    public Boolean delete(Long id);
    public File downloadAttachment(Long id) throws Exception;
}
