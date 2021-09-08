package com.nsia.officems.edu_publication.profile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.edu_publication.profile.dto.TeacherProfileDto;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface TeacherProfileService {
    public Object getList(DataTablesInput input, Map<String, String> filters);
    public TeacherProfile findById(Long id);
    public Map<String, Object> findDetailsById(Long id);
    // public boolean delete(long id);
    public Map<String, Object>  create(TeacherProfileDto profileDto);
    public Map<String, Object>  update(TeacherProfileDto profileDto, Long id);
    public File printSummary(Long id)  throws IOException, InterruptedException;
    }
