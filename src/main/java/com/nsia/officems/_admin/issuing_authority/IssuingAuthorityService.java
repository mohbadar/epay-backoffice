package com.nsia.officems._admin.issuing_authority;

import com.nsia.officems.base.LookupProjection;

import java.util.List;

public interface IssuingAuthorityService {
    public List<IssuingAuthority> findAll();
    public IssuingAuthority findById(Long id);
    public IssuingAuthority create(IssuingAuthority type);
    public Boolean delete(Long id);
    public LookupProjection getOne(Long id);
    public List<LookupProjection> getAll();
}
