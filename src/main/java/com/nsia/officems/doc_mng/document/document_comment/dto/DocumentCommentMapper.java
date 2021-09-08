package com.nsia.officems.doc_mng.document.document_comment.dto;

import com.nsia.officems.doc_mng.document.DocumentService;
import com.nsia.officems.doc_mng.document.document_comment.DocumentComment;

public class DocumentCommentMapper {

    public static DocumentComment map(DocumentCommentDto dto, DocumentComment comment, DocumentService documentService)
    {
        if(comment == null)
            comment = new DocumentComment();

        comment.setComment(dto.getComment());
        comment.setDocument(dto.getDocumentId() == null ? null : documentService.findById(dto.getDocumentId()));
        return comment;

    }
}
