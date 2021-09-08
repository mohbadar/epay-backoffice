package com.nsia.officems._admin.article_type;


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
@RequestMapping(value = "/api/article-type")
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService publicationTypeService;

    @GetMapping()
    public List<ArticleType> findAll(){
        return publicationTypeService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ArticleType findById(@PathVariable("id") Long id){
        return publicationTypeService.findById(id);
    }

    @GetMapping("/get")
    public List<LookupProjection> getAll() {
        return publicationTypeService.getAll();
    }

    @PostMapping()
    public ArticleType create(@RequestBody ArticleType type ){
        System.out.println("Create Controller");
        return publicationTypeService.create(type);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(publicationTypeService.delete(id));
    }
    
}
