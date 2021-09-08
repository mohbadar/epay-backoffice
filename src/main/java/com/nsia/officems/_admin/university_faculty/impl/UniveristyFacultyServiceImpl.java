package com.nsia.officems._admin.university_faculty.impl;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.nsia.officems._admin.university_faculty.UniversityFaculty;
import com.nsia.officems._admin.university_faculty.UniversityFacultyRepository;
import com.nsia.officems._admin.university_faculty.UniversityFacultyService;
import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems.base.LookupProjection;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniveristyFacultyServiceImpl implements UniversityFacultyService{
    @Autowired
    private UniversityFacultyRepository universityFacultyRepository;

    @Autowired
    private CustomUserService userService;

    @Override
    public List<UniversityFaculty> findAll() {
        return universityFacultyRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }
    
    @Override
    public List<LookupProjection> getAll() {
        String lang = userService.getCurrentLang();
        List<LookupProjection> projection;
        switch (lang) {
            case "ps":
                projection = universityFacultyRepository.findAllPs();
                break;
            case "en":
                projection = universityFacultyRepository.findAllEn();
                break;
            default:
                projection = universityFacultyRepository.findAllDr();
                break;

        }
        return projection;
    }

    @Override
    public UniversityFaculty findById(Long id) {
        Optional<UniversityFaculty> optionalObj = universityFacultyRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public List<LookupProjection> findByUniversityId(Long id){

        return universityFacultyRepository.findByUniversityId(id);
    }
    @Override
    public LookupProjection getOne(Long id) {
        String lang = userService.getCurrentLang();
        LookupProjection projection;
        switch (lang) {
            case "ps":
                projection = universityFacultyRepository.findByIdPs(id);
                break;
            case "en":
                projection = universityFacultyRepository.findByIdEn(id);
                break;
            default:
                projection = universityFacultyRepository.findByIdDr(id);
                break;

        }
        return projection; 
    }
    @Override
    public UniversityFaculty create(UniversityFaculty level) {
        return universityFacultyRepository.save(level);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<UniversityFaculty> level = universityFacultyRepository.findById(id);

        if (level.isPresent()) {
            UniversityFaculty level2 = level.get();
            level2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            level2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            universityFacultyRepository.save(level2);
            return true;
        }

        return false;
    }
    
}
