package com.nsia.officems._admin.job;
import java.util.List;

import com.nsia.officems.base.LookupProjection;

public interface JobService {
    public List<Job> findAll();
    public Job findById(Long id);
    public Job create(Job job);
    public Boolean delete(Long id);
    public Job update(Long id, Job job);

    public LookupProjection getOne(Long id);
    public List<LookupProjection> getAll();
}