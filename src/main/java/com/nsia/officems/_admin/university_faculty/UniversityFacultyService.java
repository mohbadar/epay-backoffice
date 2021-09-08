package com.nsia.officems._admin.university_faculty;

import java.util.List;

import com.nsia.officems.base.LookupProjection;

public interface UniversityFacultyService {
    public List<UniversityFaculty> findAll();
    public UniversityFaculty findById(Long id);
    public List<LookupProjection> findByUniversityId(Long id);
    public LookupProjection getOne(Long id);
    public List<LookupProjection> getAll();
    public UniversityFaculty create(UniversityFaculty level);
    public Boolean delete(Long id);
}
