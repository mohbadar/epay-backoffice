package com.nsia.officems.doc_mng.document.document_receive.impl;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.FileUploadUtil;
import com.nsia.officems.doc_mng.document.DocumentService;
import com.nsia.officems.doc_mng.document.document_receive.DocumentReceive;
import com.nsia.officems.doc_mng.document.document_receive.DocumentReceiveRepository;
import com.nsia.officems.doc_mng.document.document_receive.DocumentReceiveService;
import com.nsia.officems.doc_mng.document.document_receive.dto.DocumentReceiveDto;
import com.nsia.officems.doc_mng.document.document_receive.dto.DocumentReceiveMapper;

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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class DocumentReceiveServiceImpl implements DocumentReceiveService{
    @Value("${app.upload.doc_mng.documents.incoming}")
    private String uploadDir;
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private DocumentReceiveRepository repo;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Autowired
    private UserService userService;

    @Override
    public DocumentReceive findById(Long id) {
        Optional<DocumentReceive> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public DocumentReceive save(DocumentReceive executionReview) {
        return repo.save(executionReview);
    }

    public List<DocumentReceive> saveAll(List<DocumentReceive> executionReviews) {
        return repo.saveAll(executionReviews);
    }

    @Override
    public DocumentReceive create(DocumentReceive documentComment) {
        return save(documentComment);
    }

    @Override
    public List<DocumentReceive> findByDocumentIdAndDepartmentIdIn(Long documentId, List<Long> departmentIds) {
        return repo.findByDocumentIdAndDepartmentIdIn(documentId, departmentIds);
    }

    // @Override
    // public DocumentReceive updateReviewDecision(Long id, DocumentReceiveDto dto) {
    //     User currentLoginUser = userService.getLoggedInUser();
    //     DocumentReceive docReview = findById(id);
    //     if(!currentLoginUser.getId().equals(docReview.getReviewer().getId())) {
    //         throw new AccessDeniedException("Only reviewer can set decision");
    //     }

    //     docReview = DocumentReceiveMapper.map(dto, docReview, documentService, userService);
    //     return save(docReview);
    // }








    // @Override
    // public DocumentReceive create(String data, MultipartFile file) {
    //     try{
    //         Date newDate = new Date();
    //         JsonNode root = mapper.readTree(data);
    //         DocumentReceive upload = DocumentReceive.builder()
    //             // .proposal(DocumentReceiveService.findById(root.get("proposal").asLong()))
    //             .user(userService.getLoggedInUser())
    //             .title(root.get("title").asText())
    //             .tag(root.get("tag").asInt())
    //             .content(root.get("content").asText())
    //             .date(newDate)
    //             .build();
            
    //         if(!upload.equals(null)){
    //             DocumentReceive newObj = repo.save(upload);
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
            Optional <DocumentReceive> upload = repo.findById(id);
            
            if(upload.isPresent()){
                DocumentReceive newObj = upload.get();
                

                newObj.setCreatedBy(userService.getLoggedInUser());
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
        Optional<DocumentReceive> commentUpload = repo.findById(id);

        if (commentUpload.isPresent()) {
            DocumentReceive newObj = commentUpload.get();
            // newObj.setDeleted(true);
            // newObj.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            repo.save(newObj);
            return true;
        }
        return false;
    }

    @Override
    public List<DocumentReceive> findByDocumentId(Long documentId) {
        // return repo.findByDocumentId(documentId);
        return null;
    }

    

    @Override
    public DocumentReceive create(DocumentReceiveDto documentCommentDto) {
        DocumentReceive documentComment = DocumentReceiveMapper.map(documentCommentDto, new DocumentReceive(), documentService, userService);
        documentComment.setCreatedBy(userService.getLoggedInUser());
        return create(documentComment);
    }

}
