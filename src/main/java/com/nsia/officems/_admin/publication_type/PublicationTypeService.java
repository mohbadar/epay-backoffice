package com.nsia.officems._admin.publication_type;

import java.util.List;

import com.nsia.officems.base.LookupProjection;

public interface PublicationTypeService {
    public List<PublicationType> findAll();
    public PublicationType findById(Long id);
    public PublicationType create(PublicationType type);
    public Boolean delete(Long id);
    public LookupProjection getOne(Long id);
    public List<LookupProjection> getAll();
}
