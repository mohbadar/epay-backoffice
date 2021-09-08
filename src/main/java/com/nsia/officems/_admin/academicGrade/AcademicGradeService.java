package com.nsia.officems._admin.academicGrade;

import java.util.List;

import com.nsia.officems.base.LookupProjection;

public interface AcademicGradeService {
    public List<AcademicGrade> findAll();
    public AcademicGrade findById(Long id);
    public LookupProjection getOne(Long id);
    public List<LookupProjection> getAll();
    public AcademicGrade create(AcademicGrade obj);
    public Boolean delete(Long id);
}
