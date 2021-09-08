package com.nsia.officems.edu_publication.profile_job;

import java.util.List;

import com.nsia.officems.edu_publication.profile_job.Dto.ProfileJobDto;


public interface ProfileJobService {
    public List<ProfileJob> findAll();
    public ProfileJob findById(Long id);
    public ProfileJob create(ProfileJobDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, ProfileJobDto dto); 
    public List<ProfileJob> findByProfile_id(Long id);
    public ProfileJob findLastJobByProfileId(Long id);
}
