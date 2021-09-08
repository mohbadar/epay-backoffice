package com.nsia.officems.reception.visitor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
@RequestMapping("/api/reception/visitors")
public class VisitorController {
    @Autowired
    private VisitorService visitorService;

    @Autowired
    private ObjectMapper objectMapper;

    @PreAuthorize("hasAuthority('RECEP_VISITOR_LIST')")
    @PostMapping("/list")
    public Object getVisitList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside getVisitList " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return visitorService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @PreAuthorize("hasAuthority('RECEP_VISITOR_VIEW')")
    @GetMapping(value = "/{visitorId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "visitorId", required = true) long id) {
        System.out.println("Visitor " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        Visitor visitor = visitorService.findById(id);
        data.put("objection", visitor);
        return ResponseEntity.ok(data);
    }

    @PreAuthorize("hasAuthority('RECEP_VISITOR_EDIT')")
    @PutMapping(value = "/process/{visitId}/{visitorId}")
    public ResponseEntity<Visitor> processScheduledVisitor(@PathVariable(name = "visitId", required = true) Long visitId,
            @PathVariable(name = "visitorId", required = true) Long visitorId,
            @RequestBody(required = true) String body) throws JSONException, IOException {
        System.out.println("visitId: " + visitId + " visitorId: " + visitorId);
        Visitor visitor = visitorService.processVisitor(visitId, visitorId, body);
        visitor.setVisitVisitors(null);
        return ResponseEntity.ok(visitor);
    }

    @PreAuthorize("hasAuthority('RECEP_VISITOR_EDIT')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Visitor> update(@PathVariable(name = "id", required = true) Long id,
            @RequestBody(required = true) Visitor visitor) {
        System.out.println("ID: " + id + " Visit: " + visitor);
        // TODO
        return null;
    }

    @PreAuthorize("hasAuthority('RECEP_VISITOR_CREATE')")
    @PostMapping()
    public ResponseEntity<Visitor> create(@RequestBody String body) throws JSONException, IOException {
        return ResponseEntity.ok(visitorService.create(null, false));
    }
}