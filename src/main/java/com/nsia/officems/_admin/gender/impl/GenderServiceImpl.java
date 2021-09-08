package com.nsia.officems._admin.gender.impl;

import com.nsia.officems._admin.gender.Gender;
import com.nsia.officems._admin.gender.GenderRepository;
import com.nsia.officems._admin.gender.GenderService;
import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems.base.LookupProjection;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderServiceImpl implements GenderService{
    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private CustomUserService userService;

    @Override
    public List<Gender> findAll() {
        return genderRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public List<LookupProjection> getAll() {
        String lang = userService.getCurrentLang();
        List<LookupProjection> projection;
        switch (lang) {
            case "ps":
                projection = genderRepository.findAllPs();
                break;
            case "en":
                projection = genderRepository.findAllEn();
                break;
            default:
                projection = genderRepository.findAllDr();
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
                projection = genderRepository.findByIdPs(id);
                break;
            case "en":
                projection = genderRepository.findByIdEn(id);
                break;
            default:
                projection = genderRepository.findByIdDr(id);
                break;

        }
        return projection;     
    }
    @Override
    public Gender findById(Long id) {
        Optional<Gender> optionalObj = genderRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Gender create(Gender gender) {
        return genderRepository.save(gender);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<Gender> gender = genderRepository.findById(id);

        if (gender.isPresent()) {
            Gender gender2 = gender.get();
            gender2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            gender2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            genderRepository.save(gender2);
            return true;
        }

        return false;
    } 
}
