package com.nsia.officems.doc_mng.document_type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems.doc_mng.document_type.dto.CreateDocumentType;
import com.nsia.officems.doc_mng.document_type.proj.DocumentTypeLookupProj;

@RestController
@RequestMapping("/api/doc_mng/document_types")
public class DocumentTypeController {

    private final ModelMapper _modelMapper;

    @Autowired
    private DocumentTypeService documentTypeService;

    @Autowired
    private ObjectMapper objectMapper;

    public DocumentTypeController(ModelMapper modelMapper) {
        _modelMapper = modelMapper;
    }

    @PostMapping("/list")
    public Object getRecordsList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside GetFileList " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return documentTypeService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @GetMapping()
    public List<DocumentTypeLookupProj> findAll() {
        return documentTypeService.findAll();
        // return documentTypeService.findAllByLang();
    }

    @GetMapping(path = { "/type/{type}" })
    public List<DocumentTypeLookupProj> findByType(@PathVariable("type") String type) {
        return documentTypeService.findByType(type);
    }

    @GetMapping(path = { "/{id}" })
    public DocumentType findById(@PathVariable("id") Long id) {
        return documentTypeService.findById(id);
    }

    @PostMapping()
    public DocumentType create(@RequestBody CreateDocumentType createDocumentType) {
        System.out.println("Create Controller");
        DocumentType documentType = _modelMapper.map(createDocumentType, DocumentType.class);
        return documentTypeService.create(documentType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(documentTypeService.delete(id));
    }

    @PutMapping(path = "/{id}")
    public boolean update(@PathVariable(value = "id") Long id,
            @Valid @RequestBody CreateDocumentType editDocumentType) {
        DocumentType documentType = _modelMapper.map(editDocumentType, DocumentType.class);
        return documentTypeService.update(id, documentType);
    }

}