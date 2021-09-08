package com.nsia.officems.doc_mng.document.document_attachment;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nsia.officems._util.FileDownloadUtil;
import com.nsia.officems.doc_mng.document.Document;
import com.nsia.officems.doc_mng.document.DocumentService;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doc_mng/document-attachments")
public class DocumentAttachmentController {

    @Value("${app.upload.doc_mng.documents}")
    private String uploadDir;

    @Autowired
    private DocumentAttachmentService documentAttachmentService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private FileDownloadUtil fileDownloadUtil;

    @GetMapping("/{documentId}")
    public List<DocumentAttachment> findByDocumentId(@PathVariable("documentId") Long id) {
        return documentAttachmentService.findByDocumentId(id);
    }

    @PostMapping(path = "/upload-file/{documentId}/{fileName}")
    public String uploadAgendaTopicAttachment(@PathVariable(value = "documentId") Long documentId,
            @PathVariable(value = "fileName") String fileName, @RequestBody MultipartFile file) throws IOException {
        Document document = documentService.findById(documentId);
        if (document != null) {
            String uploadDirectory = uploadDir + "/" + document.getId().toString();
            String fileLocation = documentAttachmentService.saveAttachment(uploadDirectory, file);
            DocumentAttachment updateDocumentAttachment = documentAttachmentService.create(documentId, fileName,
                    fileLocation);
            if (updateDocumentAttachment != null) {
                return fileLocation;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @DeleteMapping("delete/{documentAttachmentId}")
    public Boolean deleteAgendaTopicAttachment(@PathVariable("documentAttachmentId") Long id) {
        return documentAttachmentService.deleteDocumentAttachmentById(id);
    }

    @GetMapping(value = "/download-file/{documentAttachmentId}")
    public void downloadAgendaTopicAttachment(@PathVariable(value = "documentAttachmentId") Long documentAttachmentId,
            HttpServletResponse response) throws Exception {
        File file = documentAttachmentService.downloadAttachment(documentAttachmentId);
        fileDownloadUtil.fileDownload(file, response);
    }
}
