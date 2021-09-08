package com.nsia.officems.doc_mng.document.document_followup.document_followup_activity;

import com.nsia.officems._identity.authentication.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocFollowUpActivityRepository extends JpaRepository<DocFollowUpActivity, Long> {
    DocFollowUpActivity findTopByIsFinalAndDocumentFollowUp_idOrderByCreatedAtDesc(Boolean isFinal, Long docFollowUpId);
    List<DocFollowUpActivity> findByDocumentFollowUp_idOrderByCreatedAt(Long docFollowId);
}
