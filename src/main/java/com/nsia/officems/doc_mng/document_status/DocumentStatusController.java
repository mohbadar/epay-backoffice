package com.nsia.officems.doc_mng.document_status;

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

@RestController
@RequestMapping(value = "/api/doc_mng/document_statuses")
public class DocumentStatusController {

    @Autowired
    private DocumentStatusService service;

    @GetMapping()
    public List<DocumentStatus> findAll(){
        return service.findAll();
    }


    @PostMapping()
    public DocumentStatus create(@RequestBody DocumentStatus type ){
        System.out.println("Create Controller");
        return service.create(type);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
    
}
