package com.nsia.officems._admin.researchSubjectLanguage;

import java.util.List;

import com.nsia.officems.base.LookupProjection;

public interface ResearchSubjectLanguageService {
    public List<ResearchSubjectLanguage> findAll();
    public ResearchSubjectLanguage findById(Long id);
    public LookupProjection getOne(Long id);
    public List<LookupProjection> getAll();
    public ResearchSubjectLanguage create(ResearchSubjectLanguage obj);
    public Boolean delete(Long id);
}
