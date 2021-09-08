package com.nsia.officems._admin.education_level.impl;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.nsia.officems._admin.education_level.EducationLevel;
import com.nsia.officems._admin.education_level.EducationLevelRepository;
import com.nsia.officems._admin.education_level.EducationLevelService;
import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems.base.LookupProjection;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationLevelServiceImpl implements EducationLevelService{
    @Autowired
    private EducationLevelRepository educationLevelRepository;

    @Autowired
    private CustomUserService customUserService;

    @Override
    public List<EducationLevel> findAll() {
        return educationLevelRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public EducationLevel findById(Long id) {
        Optional<EducationLevel> optionalObj = educationLevelRepository.findById(id);
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
                projection = educationLevelRepository.findAllPs();
                break;
            case "en":
                projection = educationLevelRepository.findAllEn();
                break;
            default:
                projection = educationLevelRepository.findAllDr();
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
                projection = educationLevelRepository.findByIdPs(id);
                break;
            case "en":
                projection = educationLevelRepository.findByIdEn(id);
                break;
            default:
                projection = educationLevelRepository.findByIdDr(id);
                break;

        }
        return projection;     
    }
    @Override
    public EducationLevel create(EducationLevel level) {
        return educationLevelRepository.save(level);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<EducationLevel> level = educationLevelRepository.findById(id);

        if (level.isPresent()) {
            EducationLevel level2 = level.get();
            level2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            level2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            educationLevelRepository.save(level2);
            return true;
        }

        return false;
    }
    
}
