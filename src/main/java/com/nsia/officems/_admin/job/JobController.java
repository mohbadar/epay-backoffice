package com.nsia.officems._admin.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.google.gson.Gson;
import com.nsia.officems.base.LookupProjection;


@RestController
@RequestMapping(value = "/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping()
    public List<Job> findAll(){
        return jobService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public Job findById(@PathVariable("id") Long id){
        return jobService.findById(id);
    }

    @GetMapping("/get")
    public List<LookupProjection> getAll() {
        return jobService.getAll();
    }
    
    @PostMapping()
    public Job create(@RequestBody Job job ){
        System.out.println("Create Controller");
        return jobService.create(job);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> update(@PathVariable("id") Long id, @RequestBody String job) {
        Gson g = new Gson();
        Job c = g.fromJson(job, Job.class);
        if(c != null) {
            return ResponseEntity.ok(jobService.update(id, c));
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(jobService.delete(id));
    }
}
