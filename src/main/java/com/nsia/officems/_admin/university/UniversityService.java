package com.nsia.officems._admin.university;

import java.util.List;

import com.nsia.officems.base.LookupProjection;

public interface UniversityService {
    public List<University> findAll();
    public University findById(Long id);
    public LookupProjection getOne(Long id);
    public List<LookupProjection> getAll();

    public University create(University level);
    public Boolean delete(Long id);
}
