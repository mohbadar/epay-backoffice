package com.nsia.officems.doc_mng.document_gallery;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentGalleryService {

    public Object getList(DataTablesInput input, Map<String, String> filters);

    public DocumentGallery save(DocumentGallery visit);

    public List<DocumentGallery> findAll();

    public DocumentGallery findById(Long id);

    public DocumentGallery create(String filename, String attachmentName);

    public Boolean delete(Long id);

    public String saveAttachment(String uploadDirectory, MultipartFile file);

    public File downloadAttachment(Long imageId);

    public File downloadThumbnail(Long imageId);

    public DocumentGallery update(Long imageId, String filename, String attachmentName);

    public DocumentGallery updateImageName(Long imageId, String filename);
}
