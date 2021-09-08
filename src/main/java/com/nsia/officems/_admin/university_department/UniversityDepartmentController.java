package com.nsia.officems._admin.university_department;

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
@RequestMapping(value = "/api/university-department")
public class UniversityDepartmentController {
    @Autowired
    private UniversityDepartmentService universityService;

    @GetMapping()
    public List<UniversityDepartment> findAll(){
        return universityService.findAll();
    }
    @GetMapping("/get")
    public List<LookupProjection> getAll() {
        return universityService.getAll();
    }
    @GetMapping(path = {"/{id}"})
    public UniversityDepartment findById(@PathVariable("id") Long id){
        return universityService.findById(id);
    }
    @GetMapping(path = {"/{id}/details"})
    public LookupProjection findDetailsById(@PathVariable("id") Long id){
        return universityService.getOne(id);
    }
    @GetMapping(path = {"/{id}/departments"})
    public List<LookupProjection> findByUniversityId(@PathVariable("id") Long id){
        return universityService.findByFacultyId(id);
    }
    @PostMapping()
    public UniversityDepartment create(@RequestBody UniversityDepartment obj ){
        System.out.println("Create Controller");
        return universityService.create(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(universityService.delete(id));
    }
}
