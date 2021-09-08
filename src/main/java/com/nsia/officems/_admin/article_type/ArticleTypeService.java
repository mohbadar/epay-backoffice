package com.nsia.officems._admin.article_type;

import java.util.List;

import com.nsia.officems.base.LookupProjection;

public interface ArticleTypeService {
    public List<ArticleType> findAll();
    public ArticleType findById(Long id);
    public ArticleType create(ArticleType type);
    public Boolean delete(Long id);
    public LookupProjection getOne(Long id);
    public List<LookupProjection> getAll();
}
