package com.nsia.officems.doc_mng.document.document_review.impl;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.FileUploadUtil;
import com.nsia.officems.doc_mng.document.Document;
import com.nsia.officems.doc_mng.document.DocumentService;
import com.nsia.officems.doc_mng.document.document_review.DocumentReview;
import com.nsia.officems.doc_mng.document.document_review.DocumentReviewRepository;
import com.nsia.officems.doc_mng.document.document_review.DocumentReviewService;
import com.nsia.officems.doc_mng.document.document_review.dto.DocumentReviewDto;
import com.nsia.officems.doc_mng.document.document_review.dto.DocumentReviewMapper;

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
public class DocumentReviewServiceImpl implements DocumentReviewService{
    @Value("${app.upload.doc_mng.documents.incoming}")
    private String uploadDir;
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private DocumentReviewRepository repo;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Autowired
    private UserService userService;

    @Override
    public DocumentReview findById(Long id) {
        Optional<DocumentReview> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public DocumentReview save(DocumentReview executionReview) {
        return repo.save(executionReview);
    }

    public List<DocumentReview> saveAll(List<DocumentReview> executionReviews) {
        return repo.saveAll(executionReviews);
    }

    @Override
    public DocumentReview create(DocumentReview documentComment) {
        return save(documentComment);
    }

    @Override
    public DocumentReview updateReviewDecision(Long id, DocumentReviewDto dto) {
        User currentLoginUser = userService.getLoggedInUser();
        DocumentReview docReview = findById(id);
        if(!currentLoginUser.getId().equals(docReview.getReviewer().getId())) {
            throw new AccessDeniedException("Only reviewer can set decision");
        }

        docReview = DocumentReviewMapper.map(dto, docReview, documentService, userService);
        return save(docReview);
    }

    public DocumentReview resetReviewDecision(Long id) {
        User currentLoginUser = userService.getLoggedInUser();
        DocumentReview docReview = findById(id);
        if(!currentLoginUser.getId().equals(docReview.getCreatedBy().getId())) {
            throw new AccessDeniedException("Only document owner can reset REJECTED decision");
        }
        Document document = docReview.getDocument();
        if(!document.getStatus().equals("DRAFT")) {
            throw new AccessDeniedException("Only document owner can reset REJECTED decision during Draft only");
        }
        docReview.setReviewedAt(null);
        docReview.setComment("");
        docReview.setDecision("");
        return save(docReview);
    }







    // @Override
    // public DocumentReview create(String data, MultipartFile file) {
    //     try{
    //         Date newDate = new Date();
    //         JsonNode root = mapper.readTree(data);
    //         DocumentReview upload = DocumentReview.builder()
    //             // .proposal(DocumentReviewService.findById(root.get("proposal").asLong()))
    //             .user(userService.getLoggedInUser())
    //             .title(root.get("title").asText())
    //             .tag(root.get("tag").asInt())
    //             .content(root.get("content").asText())
    //             .date(newDate)
    //             .build();
            
    //         if(!upload.equals(null)){
    //             DocumentReview newObj = repo.save(upload);
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
            Optional <DocumentReview> upload = repo.findById(id);
            
            if(upload.isPresent()){
                DocumentReview newObj = upload.get();
                

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
        Optional<DocumentReview> commentUpload = repo.findById(id);

        if (commentUpload.isPresent()) {
            DocumentReview newObj = commentUpload.get();
            // newObj.setDeleted(true);
            // newObj.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            repo.save(newObj);
            return true;
        }
        return false;
    }

    @Override
    public List<DocumentReview> findByDocumentId(Long documentId) {
        // return repo.findByDocumentId(documentId);
        return null;
    }

    

    @Override
    public DocumentReview create(DocumentReviewDto documentCommentDto) {
        DocumentReview documentComment = DocumentReviewMapper.map(documentCommentDto, new DocumentReview(), documentService, userService);
        documentComment.setCreatedBy(userService.getLoggedInUser());
        return create(documentComment);
    }

}
