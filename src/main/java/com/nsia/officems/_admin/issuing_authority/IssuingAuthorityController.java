package com.nsia.officems._admin.issuing_authority;

import com.nsia.officems.base.LookupProjection;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/_admin/issuing-authorities")
public class IssuingAuthorityController {
    private final ModelMapper _modelMapper;

    @Autowired
    private IssuingAuthorityService issuingAuthorityService;

//    @GetMapping()
//    public List<LookupProjection> findAll() {
//        return issuingAuthorityService.getAll();
//    }

    @GetMapping()
    public List<IssuingAuthority> findAll() {

        return issuingAuthorityService.findAll();
    }

    @GetMapping(path = { "/{id}" })
    public IssuingAuthority findById(@PathVariable("id") Long id) {
        return issuingAuthorityService.findById(id);
    }

    @PostMapping()
    public IssuingAuthority create(@RequestBody IssuingAuthority issuingAuthority ) {
        System.out.println("Create Controller");
        return issuingAuthorityService.create(issuingAuthority);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(issuingAuthorityService.delete(id));
    }



}
