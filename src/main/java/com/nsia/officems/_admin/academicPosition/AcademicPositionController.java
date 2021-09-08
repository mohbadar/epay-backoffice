package com.nsia.officems._admin.academicPosition;

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
@RequestMapping(value = "/api/academic-position")
public class AcademicPositionController {
    @Autowired
    private AcademicPositionService academicPositionService;

    @GetMapping()
    public List<AcademicPosition> findAll(){
        return academicPositionService.findAll();
    }

    @GetMapping("/get")
    public List<LookupProjection> getAll() {
        return academicPositionService.getAll();
    }
    @GetMapping(path = {"/{id}"})
    public AcademicPosition findById(@PathVariable("id") Long id){
        return academicPositionService.findById(id);
    }

    @GetMapping(path = {"/{id}/details"})
    public LookupProjection findDetailsById(@PathVariable("id") Long id){
        return academicPositionService.getOne(id);
    }
    @PostMapping()
    public AcademicPosition create(@RequestBody AcademicPosition obj ){
        System.out.println("Create Controller");
        return academicPositionService.create(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(academicPositionService.delete(id));
    }
}
