package com.nsia.officems._admin.gender;

import java.util.List;

import com.nsia.officems.base.LookupProjection;

public interface GenderService {
    public List<Gender> findAll();
    public Gender findById(Long id);
    public LookupProjection getOne(Long id);
    public List<LookupProjection> getAll();
    public Gender create(Gender gender);
    public Boolean delete(Long id);
}
