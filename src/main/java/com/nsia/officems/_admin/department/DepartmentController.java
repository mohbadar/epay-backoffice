package com.nsia.officems._admin.department;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems._admin.department.dto.DepartmentDto;
import com.nsia.officems._admin.department.proj.DepartmentLookupProj;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/_admin/departments")
public class DepartmentController {

    private final ModelMapper _modelMapper;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping()
    public List<DepartmentLookupProj> findAllDepartments() {
        return departmentService.findAllDepartments();
    }

    @PostMapping("/list")
    public Object getRequestList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside getRequestList " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return departmentService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @GetMapping(path = { "/entities" })
    public List<DepartmentLookupProj> entitiesList() {
        return departmentService.findEntities();
    }

    @GetMapping("/direct_sub")
    public List<DepartmentLookupProj> findDirectChildDepartments(@RequestParam("pId") Long pId ) {
        return departmentService.findDirectChildDepartments(pId);
    }

    @GetMapping("/sub")
    public List<DepartmentLookupProj> findChildDepartments(@RequestParam("pId") Long pId ) {
        return departmentService.findChildDepartments(pId);
    }

    @GetMapping(path = { "/{id}" })
    public Department findById(@PathVariable("id") Long id) {
        return departmentService.findById(id);
    }

    @PostMapping()
    public Long create(@RequestBody DepartmentDto departmentDto, HttpServletRequest request) {
        return departmentService.createWithClosure(departmentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(departmentService.delete(id));
    }

     @PutMapping(path = "/{id}")
     public boolean updateDepartmentById(@PathVariable(value = "id") Long id,
             @Valid @RequestBody DepartmentDto departmentDto) {

         Department department = _modelMapper.map(departmentDto, Department.class);
         return departmentService.update(id, department);
     }

}
