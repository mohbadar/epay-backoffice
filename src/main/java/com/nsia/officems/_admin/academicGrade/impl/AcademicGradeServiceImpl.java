package com.nsia.officems._admin.academicGrade.impl;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.nsia.officems._admin.academicGrade.AcademicGrade;
import com.nsia.officems._admin.academicGrade.AcademicGradeRepository;
import com.nsia.officems._admin.academicGrade.AcademicGradeService;
import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems.base.LookupProjection;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcademicGradeServiceImpl implements AcademicGradeService{
    @Autowired
    private AcademicGradeRepository academicGradeRepository;

    @Autowired
    private CustomUserService userService;

    @Override
    public List<AcademicGrade> findAll() {
        return academicGradeRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public AcademicGrade findById(Long id) {
        Optional<AcademicGrade> optionalObj = academicGradeRepository.findById(id);
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
                projection = academicGradeRepository.findByIdPs(id);
                break;
            case "en":
                projection = academicGradeRepository.findByIdEn(id);
                break;
            default:
                projection = academicGradeRepository.findByIdDr(id);
                break;

        }
        return projection;
    }

    @Override
    public List<LookupProjection> getAll() {
        String lang = userService.getCurrentLang();
        List<LookupProjection> projection;
        switch (lang) {
            case "ps":
                projection = academicGradeRepository.findAllPs();
                break;
            case "en":
                projection = academicGradeRepository.findAllEn();
                break;
            default:
                projection = academicGradeRepository.findAllDr();
                break;

        }
        return projection;
    }
    @Override
    public AcademicGrade create(AcademicGrade obj) {
        return academicGradeRepository.save(obj);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<AcademicGrade> level = academicGradeRepository.findById(id);

        if (level.isPresent()) {
            AcademicGrade level2 = level.get();
            level2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            level2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            academicGradeRepository.save(level2);
            return true;
        }

        return false;
    }
    
}
