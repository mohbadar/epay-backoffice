package com.nsia.officems.doc_mng.document.document_receive.dto;

import java.time.LocalDateTime;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.doc_mng.document.DocumentService;
import com.nsia.officems.doc_mng.document.document_receive.DocumentReceive;

public class DocumentReceiveMapper {

    public static DocumentReceive map(DocumentReceiveDto dto, DocumentReceive review, DocumentService documentService, UserService userService)
    {
        LocalDateTime localDateTime = LocalDateTime.now();
        if(review == null)
            review = new DocumentReceive();

        // review.setComment(dto.getComment());
        // review.setDecision(dto.getDecision());
        // review.setReviewedAt(localDateTime);
        review.setDocument(dto.getDocumentId() == null ? null : documentService.findById(dto.getDocumentId()));
        return review;

    }
}
