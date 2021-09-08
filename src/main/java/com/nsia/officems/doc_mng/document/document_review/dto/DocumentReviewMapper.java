package com.nsia.officems.doc_mng.document.document_review.dto;

import java.time.LocalDateTime;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.doc_mng.document.DocumentService;
import com.nsia.officems.doc_mng.document.document_review.DocumentReview;

public class DocumentReviewMapper {

    public static DocumentReview map(DocumentReviewDto dto, DocumentReview review, DocumentService documentService, UserService userService)
    {
        LocalDateTime localDateTime = LocalDateTime.now();
        if(review == null)
            review = new DocumentReview();

        review.setComment(dto.getComment());
        review.setDecision(dto.getDecision());
        review.setReviewedAt(localDateTime);
        return review;

    }
}
