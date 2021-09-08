package com.nsia.officems.edu_publication.non_promotional_works;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.nsia.officems.edu_publication.non_promotional_works.dto.NonPromotionalWorksDto;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.web.multipart.MultipartFile;

public interface NonPromotionalWorksService {
    public List<NonPromotionalWorks> findAll();
    public NonPromotionalWorks findById(Long id);
    public NonPromotionalWorks create(NonPromotionalWorksDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, NonPromotionalWorksDto dto); 
    public Boolean updateCommissionDecision(Long id, NonPromotionalWorksDto obj); 
    public List<NonPromotionalWorks> findByProfile_id(Long id);
    public String saveAttachment(String uploadDirectory, MultipartFile file);

    public Boolean updateFileLocation(Long id,String fieldName, String fileLocation,  NonPromotionalWorks subject);
    public Object getList(DataTablesInput input, Map<String, String> filters);

    public List<NonPromotionalWorks> getSearchSubject(String value);
    public File downloadAttachment(Long id, String fieldName, String uploadDir,  NonPromotionalWorks subject)  throws Exception;

}
