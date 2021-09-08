package com.nsia.officems.doc_mng.document_gallery;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems._util.FileDownloadUtil;
import com.nsia.officems.doc_mng.document_gallery.dto.DocumentGalleryDto;

@RestController
@RequestMapping("/api/doc_mng/document_gallery")
public class DocumentGalleryController {

    @Value("${app.upload.doc_mng.gallery}")
    private String uploadDir;

    @Autowired
    private DocumentGalleryService documentGalleryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FileDownloadUtil fileDownloadUtil;

    @PostMapping("/list")
    public Object getRecordsList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside GetFileList " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return documentGalleryService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @GetMapping()
    public List<DocumentGallery> findAll() {
        return documentGalleryService.findAll();
    }

    @GetMapping(path = { "/{id}" })
    public DocumentGallery findById(@PathVariable("id") Long id) {
        return documentGalleryService.findById(id);
    }

    @PostMapping(path = "/upload-file/{fileName}")
    public String uploadImage(@PathVariable(value = "fileName") String fileName, @RequestBody MultipartFile file)
            throws IOException {
        String uploadDirectory = uploadDir;
        String attachmentName = documentGalleryService.saveAttachment(uploadDirectory, file);
        DocumentGallery createDocumentGallery = documentGalleryService.create(fileName, attachmentName);
        if (createDocumentGallery != null) {
            return attachmentName;
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(documentGalleryService.delete(id));
    }

    @PostMapping(path = "/update-image/{imageId}/{fileName}")
    public DocumentGallery updateImage(@PathVariable(value = "fileName") String fileName,
            @PathVariable(value = "imageId") Long imageId, @RequestBody MultipartFile file) throws IOException {
        String uploadDirectory = uploadDir;
        DocumentGallery updateDocumentGallery;
        if (file != null) {
            String attachmentName = documentGalleryService.saveAttachment(uploadDirectory, file);
            updateDocumentGallery = documentGalleryService.update(imageId, fileName, attachmentName);
        } else {
            updateDocumentGallery = documentGalleryService.updateImageName(imageId, fileName);
        }

        if (updateDocumentGallery != null) {
            return updateDocumentGallery;
        } else {
            return null;
        }
    }
}