package com.nsia.officems._admin.academicGrade;

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
@RequestMapping(value = "/api/academic-grade")
public class AcademicGradeController {
    @Autowired
    private AcademicGradeService academicGradeService;

    @GetMapping()
    public List<AcademicGrade> findAll() {
        return academicGradeService.findAll();
    }

    @GetMapping("/get")
    public List<LookupProjection> getAll() {
        return academicGradeService.getAll();
    }

    @GetMapping(path = { "/{id}" })
    public AcademicGrade findById(@PathVariable("id") Long id) {
        return academicGradeService.findById(id);
    }

    @GetMapping(path = { "/{id}/details" })
    public LookupProjection findDetailsById(@PathVariable("id") Long id) {
        return academicGradeService.getOne(id);
    }

    @PostMapping()
    public AcademicGrade create(@RequestBody AcademicGrade obj) {
        System.out.println("Create Controller");
        return academicGradeService.create(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(academicGradeService.delete(id));
    }
}
