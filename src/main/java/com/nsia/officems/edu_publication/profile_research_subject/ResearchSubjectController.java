package com.nsia.officems.edu_publication.profile_research_subject;


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
import com.nsia.officems.edu_publication.profile_publication.dto.ProfilePublicationDto;
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
@RequestMapping("/api/edu-publication/research-subject")
public class ResearchSubjectController {

    @Autowired
    private ResearchSubjectService researchSubjectService;

    @Value("${app.upload.researchSubject}")
    private String uploadDir;

    
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FileDownloadUtil fileDownloadUtil;

    @GetMapping()
    public List<ResearchSubject> findAll(){
        return researchSubjectService.findAll();
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
            return researchSubjectService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }
    
    @GetMapping("/profile")
    public List<ResearchSubject> getEPublicationByProfile(@RequestParam("pId") Long pId ) {
        return researchSubjectService.findByProfile_id(pId);
    }

     
    @GetMapping("/{id}/send-commission")
    public ResponseEntity<ResearchSubject> sendToCommission(@PathVariable("id") Long id ) {
        return ResponseEntity.ok(researchSubjectService.sendToCommission(id));
    }

    @GetMapping(value = "/{subjectId}")
    public ResponseEntity<ResearchSubject> findById(@PathVariable(name = "subjectId", required = true) long id) {
       
        return ResponseEntity.ok(researchSubjectService.findById(id));
    }
    @PutMapping(value = "/{id}/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        ResearchSubjectDto dto = g.fromJson(data, ResearchSubjectDto.class);
        return ResponseEntity.ok(researchSubjectService.update(id, dto));
        
	}

    @PutMapping(value = "/{id}/commission-decision", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> updateCommissionDecision(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        ResearchSubjectDto dto = g.fromJson(data, ResearchSubjectDto.class);
        return ResponseEntity.ok(researchSubjectService.updateCommissionDecision(id, dto));
        
	}

    @PutMapping(value = "/{id}/department-decision", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> updateDepartmentDecision(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        ResearchSubjectDto dto = g.fromJson(data, ResearchSubjectDto.class);
        return ResponseEntity.ok(researchSubjectService.updateDepartmentDecision(id, dto));
        
	}

    @PutMapping(value = "/{id}/commission-decision-complement", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> updateCommissionDecisionComplement(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        ResearchSubjectDto dto = g.fromJson(data, ResearchSubjectDto.class);
        return ResponseEntity.ok(researchSubjectService.updateCommissionDecisionComplement(id, dto));
        
	}

    

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<ResearchSubject> create(@RequestParam("data") String data) throws Exception 
    {
        Gson g = new Gson();
        ResearchSubjectDto dto = g.fromJson(data, ResearchSubjectDto.class);
        return ResponseEntity.ok(researchSubjectService.create(dto));
    }

    @PostMapping(path = "/upload/{id}")
    public String uploadAttachment(@PathVariable(value = "id") Long id, @RequestParam(name="file") MultipartFile file, @RequestParam(name = "attachment") String attachment )
            throws IOException {
        ResearchSubject researchSubject = researchSubjectService.findById(id);
        String uploadDirectory = uploadDir + "/" + researchSubject.getId().toString();
        String fileLocation = researchSubjectService.saveAttachment(uploadDirectory, file);
        Boolean fieldUpdated = researchSubjectService.updateFileLocation(id, attachment, fileLocation, researchSubject);
        if (fieldUpdated) {
            return fileLocation;
        } else
            return null;
    }

    @PostMapping("/search")
    public ResponseEntity<List<ResearchSubject>> searchSubject(@RequestBody String data) throws JSONException {
        JSONObject jso = new JSONObject(data);
        String value = jso.getString("input");
        return ResponseEntity.ok(researchSubjectService.getSearchSubject(value));
    }
    
    @GetMapping(value = "/download/{id}/{fieldName}")
    public void downloadAttachment(@PathVariable(name = "id", required = true) Long id, @PathVariable(name = "fieldName") String fieldName,
            HttpServletResponse response) throws Exception {
                ResearchSubject researchSubject = researchSubjectService.findById(id);
                String uploadDirectory = uploadDir + "/" + researchSubject.getId().toString();
        File file = researchSubjectService.downloadAttachment(id, fieldName, uploadDirectory, researchSubject);
        fileDownloadUtil.fileDownload(file, response);
    }
}
