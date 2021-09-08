package com.nsia.officems._admin.university_department.impl;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.nsia.officems._admin.university_department.UniversityDepartment;
import com.nsia.officems._admin.university_department.UniversityDepartmentRepository;
import com.nsia.officems._admin.university_department.UniversityDepartmentService;
import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems.base.LookupProjection;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniveristyDepartmentServiceImpl implements UniversityDepartmentService{
    @Autowired
    private UniversityDepartmentRepository universityDepartmentRepository;

    @Autowired
    private CustomUserService userService;

    @Override
    public List<UniversityDepartment> findAll() {
        return universityDepartmentRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public List<LookupProjection> getAll() {
        String lang = userService.getCurrentLang();
        List<LookupProjection> projection;
        switch (lang) {
            case "ps":
                projection = universityDepartmentRepository.findAllPs();
                break;
            case "en":
                projection = universityDepartmentRepository.findAllEn();
                break;
            default:
                projection = universityDepartmentRepository.findAllDr();
                break;

        }
        return projection;
    }
    @Override
    public UniversityDepartment findById(Long id) {
        Optional<UniversityDepartment> optionalObj = universityDepartmentRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }
    @Override
    public List<LookupProjection> findByFacultyId(Long id){

        return universityDepartmentRepository.findByFacultyId(id);
    }

    @Override
    public LookupProjection getOne(Long id) {

        String lang = userService.getCurrentLang();
        LookupProjection projection;
        switch (lang) {
            case "ps":
                projection = universityDepartmentRepository.findByIdPs(id);
                break;
            case "en":
                projection = universityDepartmentRepository.findByIdEn(id);
                break;
            default:
                projection = universityDepartmentRepository.findByIdDr(id);
                break;

        }
        return projection;    
    }
    @Override
    public UniversityDepartment create(UniversityDepartment obj) {
        return universityDepartmentRepository.save(obj);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<UniversityDepartment> level = universityDepartmentRepository.findById(id);

        if (level.isPresent()) {
            UniversityDepartment level2 = level.get();
            level2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            level2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            universityDepartmentRepository.save(level2);
            return true;
        }

        return false;
    }
    
}
