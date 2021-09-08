package com.nsia.officems.helpdesk.service_type;

import java.util.List;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems.helpdesk.service_type.dto.ServiceTypeDto;

@RestController
@RequestMapping("/api/helpdesk/service_types")
public class ServiceTypeController {

    @Autowired
    private ServiceTypeService serviceTypeService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping()
    public List<ServiceType> findAll() {
        return serviceTypeService.findAll();
    }

    @GetMapping(path = { "/{id}" })
    public ServiceType findById(@PathVariable("id") Long id) {
        return serviceTypeService.findById(id);
    }

    @PostMapping()
    public ServiceType create(@RequestBody ServiceTypeDto serviceTypeDto) {
        return serviceTypeService.create(serviceTypeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(serviceTypeService.delete(id));
    }

}