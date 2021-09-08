package com.nsia.officems._admin.district;

import java.util.List;

import com.nsia.officems._admin.district.Dto.DistrictDto;
import com.nsia.officems.base.LookupProjection;

public interface DistrictService {
    public List<District> findAll();
    public District findById(Long id);
    public boolean delete(Long id);
    public List<LookupProjection> findByProvince(Long id);
    public District create(DistrictDto dto);
    public District save(District obj);
    public Boolean update(Long id, DistrictDto dto); 
    public LookupProjection getOne(Long id);
    public List<LookupProjection> getAll();
}
