package com.nsia.officems._admin.gender;


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
@RequestMapping(value = "/api/genders")
public class GenderController {
    @Autowired
    private GenderService genderService;

    @GetMapping()
    public List<Gender> findAll(){
        return genderService.findAll();
    }

    @GetMapping("/get")
    public List<LookupProjection> getAll() {
        return genderService.getAll();
    }
    @GetMapping(path = {"/{id}"})
    public Gender findById(@PathVariable("id") Long id){
        return genderService.findById(id);
    }

    @GetMapping(path = {"/{id}/details"})
    public LookupProjection findDetailsById(@PathVariable("id") Long id){
        return genderService.getOne(id);
    }
    @PostMapping()
    public Gender create(@RequestBody Gender gender ){
        System.out.println("Create Controller");
        return genderService.create(gender);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(genderService.delete(id));
    }
    
}
