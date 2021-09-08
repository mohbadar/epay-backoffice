package com.nsia.officems.doc_mng.document_note_type;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems.doc_mng.document_note_type.dto.DocumentNoteTypeDto;

@RestController
@RequestMapping("/api/doc_mng/document_note_types")
public class DocumentNoteTypeController {
    
    private final ModelMapper _modelMapper;

    @Autowired
    private DocumentNoteTypeService noteTypeService;

    @Autowired
    private ObjectMapper objectMapper;

    public DocumentNoteTypeController(ModelMapper modelMapper) {
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
            return noteTypeService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @GetMapping()
    public List<DocumentNoteType> findAll() {
        return noteTypeService.findAll();
    }

    @GetMapping(path = { "/{id}" })
    public DocumentNoteType findById(@PathVariable("id") Long id) {
        return noteTypeService.findById(id);
    }

    @PostMapping()
    public DocumentNoteType create(@RequestBody DocumentNoteTypeDto dto) {
        System.out.println("Create Controller");
        DocumentNoteType noteType = _modelMapper.map(dto, DocumentNoteType.class);
        return noteTypeService.create(noteType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(noteTypeService.delete(id));
    }

    @PutMapping(path = "/{id}")
    public boolean update(@PathVariable(value = "id") Long id,
            @Valid @RequestBody DocumentNoteTypeDto dto) {
                DocumentNoteType noteType  = _modelMapper.map(dto, DocumentNoteType.class);
        return noteTypeService.update(id, noteType);
    }
}
