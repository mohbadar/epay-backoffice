package com.nsia.officems._admin.reviewStatus;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.nsia.officems.base.LookupProjection;

@RestController
@RequestMapping(value = "/api/review-status")
public class ReviewStatusController {
    @Autowired
    private ReviewStatusService reviewStatusService;

    @GetMapping()
    public List<ReviewStatus> findAll(){
        return reviewStatusService.findAll();
    }

    @GetMapping("/get")
    public List<LookupProjection> getAll() {
        return reviewStatusService.getAll();
    }
    @GetMapping(path = {"/{id}"})
    public ReviewStatus findById(@PathVariable("id") Long id){
        return reviewStatusService.findById(id);
    }

    @GetMapping(path = {"/{id}/details"})
    public LookupProjection findDetailsById(@PathVariable("id") Long id){
        return reviewStatusService.getOne(id);
    }
    @PostMapping()
    public ReviewStatus create(@RequestBody ReviewStatus gender ){
        System.out.println("Create Controller");
        return reviewStatusService.create(gender);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reviewStatusService.delete(id));
    }
    
}
