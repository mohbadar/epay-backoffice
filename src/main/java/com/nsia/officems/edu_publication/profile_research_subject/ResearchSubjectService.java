package com.nsia.officems.edu_publication.profile_research_subject;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.nsia.officems.base.LookupProjection;
import com.nsia.officems.edu_publication.profile_research_subject.dto.ResearchSubjectDto;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.web.multipart.MultipartFile;

public interface ResearchSubjectService {
    public List<ResearchSubject> findAll();
    public ResearchSubject findById(Long id);
    public ResearchSubject sendToCommission(Long id);
    public ResearchSubject create(ResearchSubjectDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, ResearchSubjectDto dto); 
    public Boolean updateCommissionDecision(Long id, ResearchSubjectDto obj); 
    public Boolean updateDepartmentDecision(Long id, ResearchSubjectDto obj); 
    public Boolean updateCommissionDecisionComplement(Long id, ResearchSubjectDto obj);
    public List<ResearchSubject> findByProfile_id(Long id);
    public String saveAttachment(String uploadDirectory, MultipartFile file);

    public Boolean updateFileLocation(Long id,String fieldName, String fileLocation,  ResearchSubject subject);
    public Object getList(DataTablesInput input, Map<String, String> filters);

    public List<ResearchSubject> getSearchSubject(String value);
    public File downloadAttachment(Long id, String fieldName, String uploadDir,  ResearchSubject subject)  throws Exception;

    public List<LookupProjection> getResearchSubjectsByProfile(Long id);

}
