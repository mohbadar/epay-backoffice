package com.nsia.officems.doc_mng.document_gallery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentGalleryRepository extends JpaRepository<DocumentGallery, Long>  {
}