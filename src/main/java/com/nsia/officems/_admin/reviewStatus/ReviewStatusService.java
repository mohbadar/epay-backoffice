package com.nsia.officems._admin.reviewStatus;

import java.util.List;

import com.nsia.officems.base.LookupProjection;

public interface ReviewStatusService {
    public List<ReviewStatus> findAll();
    public ReviewStatus findById(Long id);
    public LookupProjection getOne(Long id);
    public List<LookupProjection> getAll();
    public ReviewStatus create(ReviewStatus gender);
    public Boolean delete(Long id);
}
