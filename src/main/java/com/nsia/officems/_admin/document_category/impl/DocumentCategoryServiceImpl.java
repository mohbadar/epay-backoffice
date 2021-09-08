package com.nsia.officems._admin.document_category.impl;

import com.nsia.officems._admin.document_category.DocumentCategory;
import com.nsia.officems._admin.document_category.DocumentCategoryRepository;
import com.nsia.officems._admin.document_category.DocumentCategoryService;
import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.base.LookupProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentCategoryServiceImpl implements DocumentCategoryService {
    @Autowired
    private DocumentCategoryRepository documentCategoryRepository;

    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private UserService userService;

    @Override
    public List<DocumentCategory> findAll() {
        return documentCategoryRepository.findByDeletedFalseOrDeletedIsNull();
    }

    @Override
    public DocumentCategory findById(Long id) {
        Optional<DocumentCategory> optionalObj = documentCategoryRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public List<LookupProjection> getAll() {
        String lang = customUserService.getCurrentLang();
        List<LookupProjection> projection;
        switch (lang) {
            case "ps":
                projection = documentCategoryRepository.findAllPs();
                break;
            case "en":
                projection = documentCategoryRepository.findAllEn();
                break;
            default:
                projection = documentCategoryRepository.findAllDr();
                break;

        }
        return projection;
    }

    @Override
    public LookupProjection getOne(Long id) {
        String lang = customUserService.getCurrentLang();
        LookupProjection projection;
        switch (lang) {
            case "ps":
                projection = documentCategoryRepository.findByIdPs(id);
                break;
            case "en":
                projection = documentCategoryRepository.findByIdEn(id);
                break;
            default:
                projection = documentCategoryRepository.findByIdDr(id);
                break;

        }
        return projection;     
    }

    @Override
    public DocumentCategory create(DocumentCategory type) {
        return documentCategoryRepository.save(type);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<DocumentCategory> docCategory = documentCategoryRepository.findById(id);

        if (docCategory.isPresent()) {
            DocumentCategory documentCategory = docCategory.get();
            documentCategory.setDeleted(true);
            documentCategory.setDeletedBy(userService.getLoggedInUser().getUsername());
            documentCategory.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            documentCategoryRepository.save(documentCategory);
            return true;
        }

        return false;
    }
}
