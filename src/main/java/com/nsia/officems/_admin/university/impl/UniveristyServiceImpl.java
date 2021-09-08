package com.nsia.officems._admin.university.impl;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.nsia.officems._admin.university.University;
import com.nsia.officems._admin.university.UniversityRepository;
import com.nsia.officems._admin.university.UniversityService;
import com.nsia.officems._identity.authentication.user.CustomDigestUserService;
import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.base.LookupProjection;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniveristyServiceImpl implements UniversityService {
    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private CustomUserService userService;

    @Override
    public List<University> findAll() {

        return universityRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public List<LookupProjection> getAll() {
        String lang = userService.getCurrentLang();
        List<LookupProjection> projection;
        switch (lang) {
            case "ps":
                projection = universityRepository.findAllPs();
                break;
            case "en":
                projection = universityRepository.findAllEn();
                break;
            default:
                projection = universityRepository.findAllDr();
                break;

        }
        return projection;
    }
    @Override
    public University findById(Long id) {
        Optional<University> optionalObj = universityRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public LookupProjection getOne(Long id) {

        String lang = userService.getCurrentLang();
        LookupProjection projection;
        switch (lang) {
            case "ps":
                projection = universityRepository.findByIdPs(id);
                break;
            case "en":
                projection = universityRepository.findByIdEn(id);
                break;
            default:
                projection = universityRepository.findByIdDr(id);
                break;

        }
        return projection;
    }

    @Override
    public University create(University level) {
        return universityRepository.save(level);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<University> level = universityRepository.findById(id);

        if (level.isPresent()) {
            University level2 = level.get();
            level2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            level2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            universityRepository.save(level2);
            return true;
        }

        return false;
    }

}
