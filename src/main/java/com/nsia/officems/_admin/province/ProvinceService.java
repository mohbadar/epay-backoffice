package com.nsia.officems._admin.province;
import java.util.List;

import com.nsia.officems._admin.province.Dto.ProvinceDto;
import com.nsia.officems.base.LookupProjection;

public interface ProvinceService {
    public List<Province> findAll();
    public Province findById(Long id);
    public Boolean delete(Long id);
    public List<LookupProjection> findByCountry(Long id);
	public Province create(ProvinceDto dto);
    public Province save(Province obj);
    public Boolean update(Long id, ProvinceDto dto); 
    public LookupProjection getOne(Long id);
    public List<LookupProjection> getAll();
}
