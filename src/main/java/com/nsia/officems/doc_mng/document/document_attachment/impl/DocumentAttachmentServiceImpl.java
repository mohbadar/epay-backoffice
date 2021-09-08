package com.nsia.officems.doc_mng.document.document_attachment.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.nsia.officems.doc_mng.document.DocumentService;
import com.nsia.officems.doc_mng.document.document_attachment.DocumentAttachment;
import com.nsia.officems.doc_mng.document.document_attachment.DocumentAttachmentRepository;
import com.nsia.officems.doc_mng.document.document_attachment.DocumentAttachmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentAttachmentServiceImpl implements DocumentAttachmentService {

    @Value("${app.upload.doc_mng.documents}")
    private String uploadDir;

    @Autowired
    private DocumentAttachmentRepository documentAttachmentRepository;

    @Autowired
    private DocumentService documentService;

    @Override
    public DocumentAttachment findById(Long id) {
        Optional<DocumentAttachment> optionalObj = documentAttachmentRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public DocumentAttachment create(Long documentId, String fileName, String attachmentName) {
        DocumentAttachment documentAttachment = new DocumentAttachment();
        documentAttachment.setFileName(fileName);
        documentAttachment.setAttachmentName(attachmentName);
        documentAttachment.setDocument(documentService.findById(documentId));
        documentAttachment.setDeleted(false);
        return documentAttachmentRepository.save(documentAttachment);
    }

    @Transactional
    @Override
    public List<DocumentAttachment> findByDocumentId(Long id) {
        return documentAttachmentRepository.findByDocument_idAndDeletedFalse(id);
    }

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
            } catch (Exception e) {

            }
        }
        return fileName;
    }

    @Override
    public Boolean deleteDocumentAttachmentById(Long documentAttachmentId) {
        Optional<DocumentAttachment> documentAttachment = documentAttachmentRepository.findById(documentAttachmentId);
        if (documentAttachment.isPresent()) {
            DocumentAttachment documentAttachmentToBeUpdated = documentAttachment.get();
            documentAttachmentToBeUpdated.setDeleted(true);
            documentAttachmentRepository.save(documentAttachmentToBeUpdated);
            return true;
        }
        return false;
    }

    @Override
    public File downloadAttachment(Long documentAttachmentId) {
        DocumentAttachment documentAttachment = findById(documentAttachmentId);
        if (documentAttachment != null) {
            String file = uploadDir + "/" + documentAttachment.getDocument().getId() + "/"
                    + documentAttachment.getAttachmentName();
            if (new File(file).exists()) {
                return new File(file);
            }
        }
        return null;
    }

}
