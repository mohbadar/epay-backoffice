package com.nsia.officems._admin.document_category;

import com.nsia.officems.base.LookupProjection;

import java.util.List;

public interface DocumentCategoryService {
    public List<DocumentCategory> findAll();
    public DocumentCategory findById(Long id);
    public DocumentCategory create(DocumentCategory type);
    public Boolean delete(Long id);
    public LookupProjection getOne(Long id);
    public List<LookupProjection> getAll();
}
