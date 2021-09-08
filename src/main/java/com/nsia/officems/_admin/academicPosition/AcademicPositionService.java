package com.nsia.officems._admin.academicPosition;

import java.util.List;

import com.nsia.officems.base.LookupProjection;

public interface AcademicPositionService {
    public List<AcademicPosition> findAll();
    public AcademicPosition findById(Long id);
    public LookupProjection getOne(Long id);
    public List<LookupProjection> getAll();
    public AcademicPosition create(AcademicPosition obj);
    public Boolean delete(Long id);
}
