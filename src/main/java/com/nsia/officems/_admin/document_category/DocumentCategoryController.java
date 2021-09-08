package com.nsia.officems._admin.document_category;


import com.nsia.officems.base.LookupProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/_admin/document-categories/")
public class DocumentCategoryController {
    @Autowired
    private DocumentCategoryService documentCategoryService;

//    @GetMapping()
//    public List<LookupProjection> findAll(){
//        return documentCategoryService.getAll();
//    }
    @GetMapping()
    public List<DocumentCategory> findAll(){
        return documentCategoryService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public DocumentCategory findById(@PathVariable("id") Long id){
        return documentCategoryService.findById(id);
    }

    @GetMapping("/get")
    public List<LookupProjection> getAll() {

        return documentCategoryService.getAll();
    }

    @PostMapping()
    public DocumentCategory create(@RequestBody DocumentCategory type ){
        System.out.println("Create Controller");
        return documentCategoryService.create(type);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(documentCategoryService.delete(id));
    }
    
}
