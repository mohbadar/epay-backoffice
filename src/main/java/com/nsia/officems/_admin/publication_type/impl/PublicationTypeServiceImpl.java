package com.nsia.officems._admin.publication_type.impl;

import java.util.List;
import java.util.Optional;

import com.nsia.officems._admin.publication_type.PublicationType;
import com.nsia.officems._admin.publication_type.PublicationTypeRepository;
import com.nsia.officems._admin.publication_type.PublicationTypeService;
import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems.base.LookupProjection;

import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationTypeServiceImpl implements PublicationTypeService{
    @Autowired
    private PublicationTypeRepository publicationTypeRepository;

    @Autowired
    private CustomUserService customUserService;

    @Override
    public List<PublicationType> findAll() {
        return publicationTypeRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public PublicationType findById(Long id) {
        Optional<PublicationType> optionalObj = publicationTypeRepository.findById(id);
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
                projection = publicationTypeRepository.findAllPs();
                break;
            case "en":
                projection = publicationTypeRepository.findAllEn();
                break;
            default:
                projection = publicationTypeRepository.findAllDr();
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
                projection = publicationTypeRepository.findByIdPs(id);
                break;
            case "en":
                projection = publicationTypeRepository.findByIdEn(id);
                break;
            default:
                projection = publicationTypeRepository.findByIdDr(id);
                break;

        }
        return projection;     
    }

    @Override
    public PublicationType create(PublicationType type) {
        return publicationTypeRepository.save(type);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<PublicationType> type = publicationTypeRepository.findById(id);

        if (type.isPresent()) {
            PublicationType type2 = type.get();
            type2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            type2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            publicationTypeRepository.save(type2);
            return true;
        }

        return false;
    }
}
