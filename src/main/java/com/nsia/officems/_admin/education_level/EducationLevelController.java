package com.nsia.officems._admin.education_level;

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
@RequestMapping(value = "/api/education-level")
public class EducationLevelController {
    @Autowired
    private EducationLevelService educationLevelService;

    @GetMapping()
    public List<EducationLevel> findAll(){
        return educationLevelService.findAll();
    }

    @GetMapping("/get")
    public List<LookupProjection> getAll() {
        return educationLevelService.getAll();
    }
    @GetMapping(path = {"/{id}"})
    public EducationLevel findById(@PathVariable("id") Long id){
        return educationLevelService.findById(id);
    }

    @PostMapping()
    public EducationLevel create(@RequestBody EducationLevel level ){
        System.out.println("Create Controller");
        return educationLevelService.create(level);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(educationLevelService.delete(id));
    }
}
