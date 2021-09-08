package com.nsia.officems._admin.education_level;

import java.util.List;

import com.nsia.officems.base.LookupProjection;

public interface EducationLevelService {
    public List<EducationLevel> findAll();
    public EducationLevel findById(Long id);
    public EducationLevel create(EducationLevel level);
    public Boolean delete(Long id);

    public LookupProjection getOne(Long id);
    public List<LookupProjection> getAll();
}
