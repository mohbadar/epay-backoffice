package com.nsia.officems.doc_mng.document.document_comment.impl;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.FileUploadUtil;
import com.nsia.officems.doc_mng.document.DocumentService;
import com.nsia.officems.doc_mng.document.document_comment.DocumentComment;
import com.nsia.officems.doc_mng.document.document_comment.DocumentCommentRepository;
import com.nsia.officems.doc_mng.document.document_comment.DocumentCommentService;
import com.nsia.officems.doc_mng.document.document_comment.dto.DocumentCommentDto;
import com.nsia.officems.doc_mng.document.document_comment.dto.DocumentCommentMapper;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import java.io.File;
import java.time.ZoneId;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DocumentCommentServiceImpl implements DocumentCommentService{
    @Value("${app.upload.doc_mng.documents.incoming}")
    private String uploadDir;
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private DocumentCommentRepository repo;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Autowired
    private UserService userService;

    @Override
    public DocumentComment findById(Long id) {
        Optional<DocumentComment> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    // @Override
    // public DocumentComment create(String data, MultipartFile file) {
    //     try{
    //         Date newDate = new Date();
    //         JsonNode root = mapper.readTree(data);
    //         DocumentComment upload = DocumentComment.builder()
    //             // .proposal(DocumentCommentService.findById(root.get("proposal").asLong()))
    //             .user(userService.getLoggedInUser())
    //             .title(root.get("title").asText())
    //             .tag(root.get("tag").asInt())
    //             .content(root.get("content").asText())
    //             .date(newDate)
    //             .build();
            
    //         if(!upload.equals(null)){
    //             DocumentComment newObj = repo.save(upload);
    //             // String fileName = this.saveAttachment(file, newObj.getId());
    //             String fileName = fileUploadUtil.saveAttachment(file, uploadDir, newObj.getId().toString(), "comment");
    //             if(newObj != null){
    //                 newObj.setFileName(fileName);
                    
    //                 return repo.save(newObj);
    //             }
                
    //         }
    //         return null;
    //     }
    //     catch(Exception e){
    //         System.out.println("exception occured in creating documentUpload");
    //         return null;
    //     }
    // }

    @Override
    public Boolean update(Long id, String data, MultipartFile file){
        try{
            JsonNode root = mapper.readTree(data);
            Optional <DocumentComment> upload = repo.findById(id);
            
            if(upload.isPresent()){
                DocumentComment newObj = upload.get();
                if(file != null)
                {
                    String fileName = fileUploadUtil.saveAttachment(file, uploadDir, newObj.getId().toString(), "comment");
                    newObj.setFileName(fileName);
                }

                // newObj.setProposal(DocumentCommentService.findById(root.get("proposal").asLong()));
                newObj.setTitle(root.get("title").asText());
                newObj.setCreatedBy(userService.getLoggedInUser());
                newObj.setContent(root.get("content").asText());
                repo.save(newObj);
                return true;
                
            }
            return false;
        }
        catch(Exception e){
            System.out.println("exception occured in creating documentUpload");
            return null;
        }
    }

    @Override
    public Boolean delete(Long id) {
        Optional<DocumentComment> commentUpload = repo.findById(id);

        if (commentUpload.isPresent()) {
            DocumentComment newObj = commentUpload.get();
            // newObj.setDeleted(true);
            // newObj.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            repo.save(newObj);
            return true;
        }
        return false;
    }



    @Override
    public File downloadAttachment(Long id) throws Exception {
        Optional<DocumentComment> documentUpload = repo.findById(id);
        if(documentUpload.isPresent())        
       {
        String fileName = documentUpload.get().getFileName();
        String saveDirectory = uploadDir + "/" + id + "/" + fileName;
        if (new File(saveDirectory).exists()) {
            return new File(saveDirectory);
        }
       }

        return null;
    }

    @Override
    public List<DocumentComment> findByDocumentId(Long documentId) {
        return repo.findByDocumentId(documentId);
    }

    @Override
    public DocumentComment save(DocumentComment documentComment) {
        return repo.save(documentComment);
    }

    @Override
    public DocumentComment create(DocumentComment documentComment) {
        return save(documentComment);
    }

    @Override
    public DocumentComment create(DocumentCommentDto documentCommentDto) {
        DocumentComment documentComment = DocumentCommentMapper.map(documentCommentDto, new DocumentComment(), documentService);
        documentComment.setCreatedBy(userService.getLoggedInUser());
        return create(documentComment);
    }

}
