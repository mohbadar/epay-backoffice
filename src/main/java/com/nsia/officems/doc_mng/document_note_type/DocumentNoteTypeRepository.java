package com.nsia.officems.doc_mng.document_note_type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentNoteTypeRepository extends JpaRepository<DocumentNoteType, Long>{
    
}
