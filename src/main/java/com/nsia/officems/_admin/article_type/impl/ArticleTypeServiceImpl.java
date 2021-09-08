package com.nsia.officems._admin.article_type.impl;

import java.util.List;
import java.util.Optional;

import com.nsia.officems._admin.article_type.ArticleType;
import com.nsia.officems._admin.article_type.ArticleTypeRepository;
import com.nsia.officems._admin.article_type.ArticleTypeService;
import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems.base.LookupProjection;

import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleTypeServiceImpl implements ArticleTypeService{
    @Autowired
    private ArticleTypeRepository articleTypeRepository;

    @Autowired
    private CustomUserService customUserService;

    @Override
    public List<ArticleType> findAll() {
        return articleTypeRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public ArticleType findById(Long id) {
        Optional<ArticleType> optionalObj = articleTypeRepository.findById(id);
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
                projection = articleTypeRepository.findAllPs();
                break;
            case "en":
                projection = articleTypeRepository.findAllEn();
                break;
            default:
                projection = articleTypeRepository.findAllDr();
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
                projection = articleTypeRepository.findByIdPs(id);
                break;
            case "en":
                projection = articleTypeRepository.findByIdEn(id);
                break;
            default:
                projection = articleTypeRepository.findByIdDr(id);
                break;

        }
        return projection;     
    }

    @Override
    public ArticleType create(ArticleType type) {
        return articleTypeRepository.save(type);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<ArticleType> type = articleTypeRepository.findById(id);

        if (type.isPresent()) {
            ArticleType type2 = type.get();
            type2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            type2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            articleTypeRepository.save(type2);
            return true;
        }

        return false;
    }
}
