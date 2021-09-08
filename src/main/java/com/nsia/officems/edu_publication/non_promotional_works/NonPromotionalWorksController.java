package com.nsia.officems.edu_publication.non_promotional_works;


import org.springframework.http.MediaType;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nsia.officems._util.FileDownloadUtil;
import com.nsia.officems.base.LookupProjection;
import com.nsia.officems.edu_publication.non_promotional_works.dto.NonPromotionalWorksDto;
import com.nsia.officems.edu_publication.profile_research_subject.ResearchSubjectService;
import com.nsia.officems.edu_publication.profile_research_subject.dto.ResearchSubjectDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/edu-publication/non-promotional-works")
public class NonPromotionalWorksController {

    @Autowired
    private NonPromotionalWorksService nonPromotionalService;

    @Value("${app.upload.nonPromotionalWorks}")
    private String uploadDir;

    
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FileDownloadUtil fileDownloadUtil;

    @Autowired
    private ResearchSubjectService researchSubjectService;

    @GetMapping()
    public List<NonPromotionalWorks> findAll(){
        return nonPromotionalService.findAll();
    }

    @PostMapping("/list")
    public Object getList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return nonPromotionalService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }
    
    @GetMapping("/profile")
    public List<NonPromotionalWorks> getEPublicationByProfile(@RequestParam("pId") Long pId ) {
        return nonPromotionalService.findByProfile_id(pId);
    }

    @GetMapping("/{id}/research-subjects")
    public List<LookupProjection> getResearchSubjectByProfileId(@RequestParam("pId") Long pId ) {
        return researchSubjectService.getResearchSubjectsByProfile(pId);
    }

    @GetMapping(value = "/{subjectId}")
    public ResponseEntity<NonPromotionalWorks> findById(@PathVariable(name = "subjectId", required = true) long id) {
       
        return ResponseEntity.ok(nonPromotionalService.findById(id));
    }
    @PutMapping(value = "/{id}/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        NonPromotionalWorksDto dto = g.fromJson(data, NonPromotionalWorksDto.class);
        return ResponseEntity.ok(nonPromotionalService.update(id, dto));
        
	}

    @PutMapping(value = "/{id}/commission-decision", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> updateCommissionDecision(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        NonPromotionalWorksDto dto = g.fromJson(data, NonPromotionalWorksDto.class);
        return ResponseEntity.ok(nonPromotionalService.updateCommissionDecision(id, dto));
        
	}

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<NonPromotionalWorks> create(@RequestParam("data") String data) throws Exception 
    {
        Gson g = new Gson();
        NonPromotionalWorksDto dto = g.fromJson(data, NonPromotionalWorksDto.class);
        return ResponseEntity.ok(nonPromotionalService.create(dto));
    }

    @PostMapping(path = "/upload/{id}")
    public String uploadAttachment(@PathVariable(value = "id") Long id, @RequestParam(name="file") MultipartFile file, @RequestParam(name = "attachment") String attachment )
            throws IOException {
        NonPromotionalWorks researchSubject = nonPromotionalService.findById(id);
        String uploadDirectory = uploadDir + "/" + researchSubject.getId().toString();
        String fileLocation = nonPromotionalService.saveAttachment(uploadDirectory, file);
        Boolean fieldUpdated = nonPromotionalService.updateFileLocation(id, attachment, fileLocation, researchSubject);
        if (fieldUpdated) {
            return fileLocation;
        } else
            return null;
    }

    @PostMapping("/search")
    public ResponseEntity<List<NonPromotionalWorks>> searchSubject(@RequestBody String data) throws JSONException {
        JSONObject jso = new JSONObject(data);
        String value = jso.getString("input");
        return ResponseEntity.ok(nonPromotionalService.getSearchSubject(value));
    }
    
    @GetMapping(value = "/download/{id}/{fieldName}")
    public void downloadAttachment(@PathVariable(name = "id", required = true) Long id, @PathVariable(name = "fieldName") String fieldName,
            HttpServletResponse response) throws Exception {
                NonPromotionalWorks researchSubject = nonPromotionalService.findById(id);
                String uploadDirectory = uploadDir + "/" + researchSubject.getId().toString();
        File file = nonPromotionalService.downloadAttachment(id, fieldName, uploadDirectory, researchSubject);
        fileDownloadUtil.fileDownload(file, response);
    }


}
