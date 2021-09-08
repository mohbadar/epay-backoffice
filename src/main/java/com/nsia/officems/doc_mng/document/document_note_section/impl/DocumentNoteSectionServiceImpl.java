package com.nsia.officems.doc_mng.document.document_note_section.impl;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.FileUploadUtil;
import com.nsia.officems.doc_mng.document.DocumentService;
import com.nsia.officems.doc_mng.document.document_note_section.DocumentNoteSection;
import com.nsia.officems.doc_mng.document.document_note_section.DocumentNoteSectionRepository;
import com.nsia.officems.doc_mng.document.document_note_section.DocumentNoteSectionService;
import com.nsia.officems.doc_mng.document.document_note_section.dto.DocumentNoteSectionDto;

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
public class DocumentNoteSectionServiceImpl implements DocumentNoteSectionService{
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private DocumentNoteSectionRepository repo;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Autowired
    private UserService userService;

    @Override
    public DocumentNoteSection findById(Long id) {
        Optional<DocumentNoteSection> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public DocumentNoteSection save(DocumentNoteSection documentNoteSection) {
        return repo.save(documentNoteSection);
    }

    public List<DocumentNoteSection> saveAll(List<DocumentNoteSection> documentNoteSections) {
        return repo.saveAll(documentNoteSections);
    }

    @Override
    public DocumentNoteSection create(DocumentNoteSection documentNoteSection) {
        return save(documentNoteSection);
    }


    // @Override
    // public Boolean update(Long id, String data, MultipartFile file){
    //     try{
    //         JsonNode root = mapper.readTree(data);
    //         Optional <DocumentNoteSection> upload = repo.findById(id);
            
    //         if(upload.isPresent()){
    //             DocumentNoteSection newObj = upload.get();
                

    //             newObj.setCreatedBy(userService.getLoggedInUser());
    //             repo.save(newObj);
    //             return true;
                
    //         }
    //         return false;
    //     }
    //     catch(Exception e){
    //         System.out.println("exception occured in creating documentUpload");
    //         return null;
    //     }
    // }

    // @Override
    // public Boolean delete(Long id) {
    //     Optional<DocumentNoteSection> commentUpload = repo.findById(id);

    //     if (commentUpload.isPresent()) {
    //         DocumentNoteSection newObj = commentUpload.get();
    //         // newObj.setDeleted(true);
    //         // newObj.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    //         repo.save(newObj);
    //         return true;
    //     }
    //     return false;
    // }

    // @Override
    // public List<DocumentNoteSection> findByDocumentId(Long documentId) {
    //     // return repo.findByDocumentId(documentId);
    //     return null;
    // }

    

    // @Override
    // public DocumentNoteSection create(DocumentNoteSectionDto DocumentNoteSectionDto) {
    //     DocumentNoteSection documentComment = DocumentReviewMapper.map(DocumentNoteSectionDto, new DocumentReview(), documentService, userService);
    //     documentComment.setCreatedBy(userService.getLoggedInUser());
    //     return create(documentComment);
    // }

}
