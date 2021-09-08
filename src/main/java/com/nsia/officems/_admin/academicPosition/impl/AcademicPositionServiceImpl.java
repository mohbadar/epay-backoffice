package com.nsia.officems._admin.academicPosition.impl;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.nsia.officems._admin.academicPosition.AcademicPosition;
import com.nsia.officems._admin.academicPosition.AcademicPositionRepository;
import com.nsia.officems._admin.academicPosition.AcademicPositionService;
import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems.base.LookupProjection;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcademicPositionServiceImpl implements AcademicPositionService{
    @Autowired
    private AcademicPositionRepository academicPositionRepository;

    @Autowired
    private CustomUserService userService;

    @Override
    public List<AcademicPosition> findAll() {
        return academicPositionRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }
    @Override
    public List<LookupProjection> getAll() {
        String lang = userService.getCurrentLang();
        List<LookupProjection> projection;
        switch (lang) {
            case "ps":
                projection = academicPositionRepository.findAllPs();
                break;
            case "en":
                projection = academicPositionRepository.findAllEn();
                break;
            default:
                projection = academicPositionRepository.findAllDr();
                break;

        }
        return projection;
    }

    @Override
    public AcademicPosition findById(Long id) {
        Optional<AcademicPosition> optionalObj = academicPositionRepository.findById(id);
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
                projection = academicPositionRepository.findByIdPs(id);
                break;
            case "en":
                projection = academicPositionRepository.findByIdEn(id);
                break;
            default:
                projection = academicPositionRepository.findByIdDr(id);
                break;

        }
        return projection;
    }
    @Override
    public AcademicPosition create(AcademicPosition obj) {
        return academicPositionRepository.save(obj);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<AcademicPosition> level = academicPositionRepository.findById(id);

        if (level.isPresent()) {
            AcademicPosition level2 = level.get();
            level2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            level2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            academicPositionRepository.save(level2);
            return true;
        }

        return false;
    }
    
}
