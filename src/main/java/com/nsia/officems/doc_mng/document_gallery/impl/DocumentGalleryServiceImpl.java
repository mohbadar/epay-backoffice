package com.nsia.officems.doc_mng.document_gallery.impl;

import com.nsia.officems.doc_mng.document.document_attachment.DocumentAttachment;
import com.nsia.officems.doc_mng.document_gallery.DocumentGallery;
import com.nsia.officems.doc_mng.document_gallery.DocumentGalleryRepository;
import com.nsia.officems.doc_mng.document_gallery.DocumentGalleryService;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.ZoneId;

import javax.imageio.ImageIO;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentGalleryServiceImpl implements DocumentGalleryService {

    @Value("${app.upload.doc_mng.gallery}")
    private String uploadDir;

    @Autowired
    private DocumentGalleryRepository documentGalleryRepository;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private UserService userService;

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " ";
        // To have first AND with no error
        String whereClause = "docGallery.deleted is not true";
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.doc_mng_document_gallery docGallery", null, joinClause, whereClause,
                groupByClause, input);
    }

    public DocumentGallery findById(Long id) {
        System.out.println(" DocumentGallery.findById()" + id);
        Optional<DocumentGallery> documentGallery = documentGalleryRepository.findById(id);
        if (documentGallery.isPresent()) {
            System.out.println("DocumentGallery: " + documentGallery);
            return documentGallery.get();
        }
        return null;
    }

    public boolean delete(long id) {
        Optional<DocumentGallery> documentGallery = documentGalleryRepository.findById(id);
        if (documentGallery.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public List<DocumentGallery> findAll() {
        return documentGalleryRepository.findAll();
    }

    @Override
    public DocumentGallery save(DocumentGallery documentGallery) {
        return documentGalleryRepository.save(documentGallery);
    }

    @Override
    public DocumentGallery create(String filename, String attachmentName) {
        DocumentGallery documentGallery = new DocumentGallery();
        documentGallery.setName(filename);
        documentGallery.setAttachmentName(attachmentName);
        documentGallery.setThumbnailName("thumbnail_" + attachmentName);
        documentGallery.setCreatedBy(userService.getLoggedInUser());
        return save(documentGallery);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<DocumentGallery> documentGallery = documentGalleryRepository.findById(id);

        if (documentGallery.isPresent()) {
            DocumentGallery documentGalleryToBeDeleted = documentGallery.get();
            documentGalleryToBeDeleted.setDeleted(true);
            documentGalleryToBeDeleted
                    .setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            documentGalleryRepository.save(documentGalleryToBeDeleted);
            return true;
        }

        return false;
    }

    @Override
    public String saveAttachment(String uploadDirectory, MultipartFile file) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        String fileName = null;
        if (file != null) {
            fileName = formatter.format(Calendar.getInstance().getTime()) + "_" + file.getOriginalFilename();
            String saveDirectory = uploadDirectory;
            File test = new File(saveDirectory);
            if (!test.exists()) {
                test.mkdirs();
            }
            try {
                File f = new File(saveDirectory + "/" + fileName);
                // create the file
                if (!f.exists()) {

                    f.createNewFile();
                }
                FileOutputStream fout = new FileOutputStream(f);
                fout.write(file.getBytes());
                fout.close();
                byte[] bytes = file.getBytes();

                ByteArrayInputStream imageInputStream = new ByteArrayInputStream(bytes);
                BufferedImage image = ImageIO.read(imageInputStream);
                BufferedImage thumbnail = Scalr.resize(image, 200);

                File thumbnailOut = new File(saveDirectory + "/" + "thumbnail_" + fileName);
                ImageIO.write(thumbnail, "png", thumbnailOut);
            } catch (Exception e) {

            }

        }
        return fileName;
    }

    @Override
    public File downloadAttachment(Long imageId) {
        DocumentGallery documentGallery = findById(imageId);
        if (documentGallery != null) {
            String file = uploadDir + "/" + documentGallery.getAttachmentName();
            if (new File(file).exists()) {
                return new File(file);
            }
        }
        return null;
    }

    @Override
    public File downloadThumbnail(Long imageId) {
        DocumentGallery documentGallery = findById(imageId);
        if (documentGallery != null) {
            String file = uploadDir + "/" + documentGallery.getThumbnailName();
            if (new File(file).exists()) {
                return new File(file);
            }
        }
        return null;
    }

    @Override
    public DocumentGallery update(Long imageId, String filename, String attachmentName) {
        DocumentGallery documentGallery = findById(imageId);
        if (documentGallery != null) {
            documentGallery.setName(filename);
            documentGallery.setAttachmentName(attachmentName);
            documentGallery.setThumbnailName("thumbnail_" + attachmentName);
        }
        return save(documentGallery);
    }

    @Override
    public DocumentGallery updateImageName(Long imageId, String filename) {
        DocumentGallery documentGallery = findById(imageId);
        if (documentGallery != null) {
            documentGallery.setName(filename);
        }
        return save(documentGallery);
    }

}
