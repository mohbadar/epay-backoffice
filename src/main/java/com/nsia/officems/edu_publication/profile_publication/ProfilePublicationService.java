package com.nsia.officems.edu_publication.profile_publication;

import java.util.List;
import java.util.Map;

import com.nsia.officems.edu_publication.profile_publication.dto.ProfilePublicationDto;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface ProfilePublicationService {
    public List<ProfilePublication> findAll();
    public ProfilePublication findById(Long id);
    public ProfilePublication create(ProfilePublicationDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, ProfilePublicationDto dto); 
    public List<ProfilePublication> findByProfile_id(Long id);
    public Object getList(DataTablesInput input, Map<String, String> filters);

}
