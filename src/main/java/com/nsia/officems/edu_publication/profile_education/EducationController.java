package com.nsia.officems.edu_publication.profile_education;

import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.nsia.officems.edu_publication.profile_education.Dto.EducationDto;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/edu-publication/education")
public class EducationController {

    @Autowired
    private EducationService educationService;


    @GetMapping()
    public List<Education> findAll(){
        return educationService.findAll();
    }

    @GetMapping("/profile")
    public List<Education> getEducationByProfile(@RequestParam("pId") Long pId ) {
        return educationService.findByProfile_id(pId);
    }

    @GetMapping(value = "/{educationId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "educationId", required = true) long id) {
        System.out.println("Objection " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        Education objection = educationService.findById(id);
        data.put("objection", objection);
        return ResponseEntity.ok(data);
    }


    @PutMapping(value = "/{id}/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        EducationDto dto = g.fromJson(data, EducationDto.class);
        return ResponseEntity.ok(educationService.update(id, dto));
        
	}

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Education> create(@RequestParam("data") String data) throws Exception 
    {
        Gson g = new Gson();
        EducationDto dto = g.fromJson(data, EducationDto.class);
        return ResponseEntity.ok(educationService.create(dto));
    }
    
}
