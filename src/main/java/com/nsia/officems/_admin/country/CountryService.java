package com.nsia.officems._admin.country;
import java.util.List;

import com.nsia.officems.base.LookupProjection;

public interface CountryService {
    public List<Country> findAll();
    public Country findById(Long id);
    public Country create(Country country);
    public Boolean delete(Long id);
    public Country update(Long id, Country country);

    public LookupProjection getOne(Long id);
    public List<LookupProjection> getAll();
}