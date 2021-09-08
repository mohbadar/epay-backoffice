package com.nsia.officems._admin.researchSubjectLanguage.impl;

import com.nsia.officems._admin.researchSubjectLanguage.ResearchSubjectLanguage;
import com.nsia.officems._admin.researchSubjectLanguage.ResearchSubjectLanguageRepository;
import com.nsia.officems._admin.researchSubjectLanguage.ResearchSubjectLanguageService;
import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems.base.LookupProjection;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResearchSubjectLanguageServiceImpl implements ResearchSubjectLanguageService{
    @Autowired
    private ResearchSubjectLanguageRepository rsRepository;

    @Autowired
    private CustomUserService userService;

    @Override
    public List<ResearchSubjectLanguage> findAll() {
        return rsRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public List<LookupProjection> getAll() {
        String lang = userService.getCurrentLang();
        List<LookupProjection> projection;
        switch (lang) {
            case "ps":
                projection = rsRepository.findAllPs();
                break;
            case "en":
                projection = rsRepository.findAllEn();
                break;
            default:
                projection = rsRepository.findAllDr();
                break;

        }
        return projection;
    }

    @Override
    public LookupProjection getOne(Long id) {
        String lang = userService.getCurrentLang();
        LookupProjection projection;
        switch (lang) {
            case "ps":
                projection = rsRepository.findByIdPs(id);
                break;
            case "en":
                projection = rsRepository.findByIdEn(id);
                break;
            default:
                projection = rsRepository.findByIdDr(id);
                break;

        }
        return projection;     
    }
    @Override
    public ResearchSubjectLanguage findById(Long id) {
        Optional<ResearchSubjectLanguage> optionalObj = rsRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public ResearchSubjectLanguage create(ResearchSubjectLanguage obj) {
        return rsRepository.save(obj);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<ResearchSubjectLanguage> gender = rsRepository.findById(id);

        if (gender.isPresent()) {
            ResearchSubjectLanguage gender2 = gender.get();
            gender2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            gender2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            rsRepository.save(gender2);
            return true;
        }

        return false;
    } 
}
