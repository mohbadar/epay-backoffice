package com.nsia.officems._admin.publicationSource.impl;

import java.util.List;
import java.util.Optional;

import com.nsia.officems._admin.publicationSource.PublicationSource;
import com.nsia.officems._admin.publicationSource.PublicationSourceRepository;
import com.nsia.officems._admin.publicationSource.PublicationSourceService;
import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems.base.LookupProjection;

import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationSourceServiceImpl implements PublicationSourceService{
    @Autowired
    private PublicationSourceRepository publicationSourceRepository;

    @Autowired
    private CustomUserService customUserService;

    @Override
    public List<PublicationSource> findAll() {
        return publicationSourceRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public PublicationSource findById(Long id) {
        Optional<PublicationSource> optionalObj = publicationSourceRepository.findById(id);
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
                projection = publicationSourceRepository.findAllPs();
                break;
            case "en":
                projection = publicationSourceRepository.findAllEn();
                break;
            default:
                projection = publicationSourceRepository.findAllDr();
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
                projection = publicationSourceRepository.findByIdPs(id);
                break;
            case "en":
                projection = publicationSourceRepository.findByIdEn(id);
                break;
            default:
                projection = publicationSourceRepository.findByIdDr(id);
                break;

        }
        return projection;     
    }

    @Override
    public PublicationSource create(PublicationSource type) {
        return publicationSourceRepository.save(type);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<PublicationSource> type = publicationSourceRepository.findById(id);

        if (type.isPresent()) {
            PublicationSource type2 = type.get();
            type2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            type2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            publicationSourceRepository.save(type2);
            return true;
        }

        return false;
    }
}
