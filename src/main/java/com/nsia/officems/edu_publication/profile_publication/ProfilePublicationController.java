package com.nsia.officems.edu_publication.profile_publication;


import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import com.nsia.officems.edu_publication.profile_publication.dto.ProfilePublicationDto;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/edu-publication/teacher-publication")
public class ProfilePublicationController {

    @Autowired
    private ProfilePublicationService publicationService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping()
    public List<ProfilePublication> findAll(){
        return publicationService.findAll();
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
            return publicationService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }
    
    @GetMapping("/profile")
    public List<ProfilePublication> getEPublicationByProfile(@RequestParam("pId") Long pId ) {
        return publicationService.findByProfile_id(pId);
    }

    @GetMapping(value = "/{publicationId}")
    public ResponseEntity<ProfilePublication> findById(@PathVariable(name = "publicationId", required = true) long id) {
        return ResponseEntity.ok( publicationService.findById(id));
    }
    @PutMapping(value = "/{id}/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        ProfilePublicationDto dto = g.fromJson(data, ProfilePublicationDto.class);
        return ResponseEntity.ok(publicationService.update(id, dto));
        
	}

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<ProfilePublication> create(@RequestParam("data") String data) throws Exception 
    {
        Gson g = new Gson();
        ProfilePublicationDto dto = g.fromJson(data, ProfilePublicationDto.class);
        return ResponseEntity.ok(publicationService.create(dto));
    }
    
}
