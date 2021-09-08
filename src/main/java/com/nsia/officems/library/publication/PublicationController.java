package com.nsia.officems.library.publication;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/library/publications")
public class PublicationController {
    @Autowired
    private PublicationService publicationService;

    @Autowired
    private ObjectMapper objectMapper;

    @PreAuthorize("hasAuthority('RECEP_VISIT_LIST')")
    @PostMapping("/list")
    public Object getVisitList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside getPublicationList " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return publicationService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @PreAuthorize("hasAuthority('RECEP_VISIT_VIEW')")
    @GetMapping(value = "/{publicationId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "publicationId", required = true) long id) {
        System.out.println("Publication " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        Publication publication = publicationService.findById(id);
        data.put("publication", publication);
        return ResponseEntity.ok(data);
    }

    @PreAuthorize("hasAuthority('RECEP_VISIT_EDIT')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Publication> update(@PathVariable(name = "id", required = true) Long id,
            @RequestBody(required = true) Publication publication) {
        System.out.println("ID: " + id + " Publication: " + publication);
        // TODO
        return null;
    }

    @PreAuthorize("hasAuthority('RECEP_VISIT_CREATE')")
    @PostMapping()
    public ResponseEntity<Publication> create(@RequestBody String body) throws JSONException, IOException {
        return ResponseEntity.ok(publicationService.create(body));
    }


}