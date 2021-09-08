package com.nsia.officems._admin.publicationSource;

import java.util.List;

import com.nsia.officems.base.LookupProjection;

public interface PublicationSourceService {
    public List<PublicationSource> findAll();
    public PublicationSource findById(Long id);
    public PublicationSource create(PublicationSource type);
    public Boolean delete(Long id);
    public LookupProjection getOne(Long id);
    public List<LookupProjection> getAll();
}
