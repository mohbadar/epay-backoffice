package com.nsia.officems._admin.university_department;

import java.util.List;

import com.nsia.officems.base.LookupProjection;

public interface UniversityDepartmentService {
    public List<UniversityDepartment> findAll();
    public UniversityDepartment findById(Long id);
    public List<LookupProjection> findByFacultyId(Long id);
    public LookupProjection getOne(Long id);
    public List<LookupProjection> getAll();
    public UniversityDepartment create(UniversityDepartment obj);
    public Boolean delete(Long id);
}
