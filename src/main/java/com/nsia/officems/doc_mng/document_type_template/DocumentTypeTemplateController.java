package com.nsia.officems.doc_mng.document_type_template;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems.doc_mng.document_type_template.dto.DocumentTypeTemplateDto;

@RestController
@RequestMapping("/api/doc_mng/document_type_templates")
public class DocumentTypeTemplateController {

    private final ModelMapper _modelMapper;

    @Autowired
    private DocumentTypeTemplateService documentTypeTemplateService;

    @Autowired
    private ObjectMapper objectMapper;

    public DocumentTypeTemplateController(ModelMapper modelMapper) {
        _modelMapper = modelMapper;
    }

    @GetMapping(value = "/list")
    public List<DocumentTypeTemplate> findAll() {
        return documentTypeTemplateService.findAll();
    }

    @PostMapping("/list")
    public Object getFilesList(@RequestBody String requestBody) throws Exception {
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
            return documentTypeTemplateService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @GetMapping(path = { "/{id}" })
    public ResponseEntity<Map<String, Object>> findById(@PathVariable("id") Long id) {
        Map<String, Object> data = new HashMap<String, Object>();
        DocumentTypeTemplate documentTypeTemplate = documentTypeTemplateService.findById(id);
        data.put("template", documentTypeTemplate);
        return ResponseEntity.ok(data);
    }

    @GetMapping(path = { "/docType/{docTypeId}/entity/{entityId}" })
    public ResponseEntity<Map<String, Object>> findByDocumentTypeAndEntityId(@PathVariable("docTypeId") Long docTypeId,
            @PathVariable("entityId") Long entityId) {
        Map<String, Object> data = new HashMap<String, Object>();
        DocumentTypeTemplate documentTypeTemplate = documentTypeTemplateService
                .findByDocumentTypeIdAndEntityId(docTypeId, entityId);
        data.put("template", documentTypeTemplate);
        return ResponseEntity.ok(data);
    }

    @PostMapping()
    public DocumentTypeTemplate create(@RequestBody DocumentTypeTemplateDto dto, HttpServletRequest request)
            throws Exception {
        DocumentTypeTemplate documentTypeTemplateExists = documentTypeTemplateService
                .findByDocumentTypeIdAndEntityId(dto.getDocumentTypeId(), dto.getEntityId());
        if (documentTypeTemplateExists != null) {
            throw new Exception("Document type for this entity already has a document template");
        } else {
            return documentTypeTemplateService.create(dto);
        }
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id,
            @RequestBody DocumentTypeTemplateDto dto, HttpServletRequest request) throws Exception {
        return ResponseEntity.ok(documentTypeTemplateService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(documentTypeTemplateService.delete(id));
    }

}