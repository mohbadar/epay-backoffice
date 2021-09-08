package com.nsia.officems.edu_publication.profile_education;

import java.util.List;
import java.util.Map;

import com.nsia.officems.edu_publication.profile_education.Dto.EducationDto;

public interface EducationService {
    public List<Education> findAll();
    public Education findById(Long id);
    public Education create(com.nsia.officems.edu_publication.profile_education.Dto.EducationDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, EducationDto dto); 
    public List<Education> findByProfile_id(Long id);
}
