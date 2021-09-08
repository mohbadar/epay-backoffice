package com.nsia.officems.edu_publication.profile_job;

import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.nsia.officems.edu_publication.profile_job.Dto.ProfileJobDto;

import org.springframework.beans.factory.annotation.Autowired;

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
@RequestMapping("/api/edu-publication/profile-job")
public class ProfileJobController {

    @Autowired
    private ProfileJobService profileJobService;


    @GetMapping()
    public List<ProfileJob> findAll(){
        return profileJobService.findAll();
    }

    @GetMapping("/profile")
    public List<ProfileJob> getProfileJobByProfile(@RequestParam("pId") Long pId ) {
        return profileJobService.findByProfile_id(pId);
    }

    @GetMapping("/{id}/last")
    public ProfileJob getLastByProfileId(@PathVariable("id") Long pId ) {
        return profileJobService.findLastJobByProfileId(pId);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProfileJob> findById(@PathVariable(name = "id", required = true) long id) {
        return ResponseEntity.ok(profileJobService.findById(id));
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        ProfileJobDto dto = g.fromJson(data, ProfileJobDto.class);
        return ResponseEntity.ok(profileJobService.update(id, dto));
        
	}

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<ProfileJob> create(@RequestParam("data") String data, HttpServletRequest request) throws Exception 
    {
        Gson g = new Gson();
        ProfileJobDto dto = g.fromJson(data, ProfileJobDto.class);
        return ResponseEntity.ok(profileJobService.create(dto));
    }
    
}
