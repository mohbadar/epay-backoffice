package com.nsia.officems.reception.vehicle;

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
@RequestMapping("/api/reception/vehicles")
public class ReceptionVehicleController {
    @Autowired
    private ReceptionVehicleService vehicleService;

    @Autowired
    private ObjectMapper objectMapper;

    @PreAuthorize("hasAuthority('RECEP_VEHICLE_LIST')")
    @PostMapping("/list")
    public Object getVisitList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside getVehicleList " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return vehicleService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @PreAuthorize("hasAuthority('RECEP_VEHICLE_VIEW')")
    @GetMapping(value = "/{vehicleId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "vehicleId", required = true) long id) {
        System.out.println("Vehicle " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        ReceptionVehicle vehicle = vehicleService.findById(id);
        data.put("vehicle", vehicle);
        return ResponseEntity.ok(data);
    }

    @PreAuthorize("hasAuthority('RECEP_VEHICLE_EDIT')")
    @PutMapping(value = "/process/{visitId}/{vehicleId}")
    public ResponseEntity<ReceptionVehicle> processScheduledVehicle(@PathVariable(name = "visitId", required = true) Long visitId,
            @PathVariable(name = "vehicleId", required = true) Long vehicleId,
            @RequestBody(required = true) String body) throws JSONException, IOException {
        System.out.println("visitId: " + visitId + " vehicleId: " + vehicleId);
        ReceptionVehicle vehicle = vehicleService.processVehicle(visitId, vehicleId, body);
        vehicle.setVisitVehicles(null);
        return ResponseEntity.ok(vehicle);
    }

    @PreAuthorize("hasAuthority('RECEP_VEHICLE_EDIT')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ReceptionVehicle> update(@PathVariable(name = "id", required = true) Long id,
            @RequestBody(required = true) ReceptionVehicle vehicle) {
        System.out.println("ID: " + id + " Visit: " + vehicle);
        // TODO
        return null;
    }

    @PreAuthorize("hasAuthority('RECEP_VEHICLE_CREATE')")
    @PostMapping()
    public ResponseEntity<ReceptionVehicle> create(@RequestBody String body) throws JSONException, IOException {
        return ResponseEntity.ok(vehicleService.create(null, false));
    }
}