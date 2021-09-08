package com.nsia.officems.transportation.vehicle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.transportation.driver.Driver;
import com.nsia.officems.transportation.vehicle.dto.VehicleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api/transport/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @PreAuthorize("hasAuthority('TRANS_VEHICLE_LIST')")
    @PostMapping("/list")
    public Object getVehicleList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("vehicleBody: " + input);

        System.out.println("Filter inside getVehicleList " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return  vehicleService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }


    @GetMapping(value = "/{vehicleId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "vehicleId", required = true) long id) {
        System.out.println("vehicle " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        Vehicle vehicle = vehicleService.findById(id);

        data.put("vehicle", vehicle);
        return ResponseEntity.ok(data);
    }

    @PreAuthorize("hasAuthority('TRANS_VEHICLE_EDIT')")
    @PutMapping(value = "/{id}/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestBody String data, HttpServletRequest request) throws Exception {
        Gson g = new Gson();
        VehicleDto dto = g.fromJson(data, VehicleDto.class);
        return ResponseEntity.ok(vehicleService.update(id, dto));

    }

    @PreAuthorize("hasAuthority('TRANS_VEHICLE_CREATE')")
    @PostMapping()
    public Vehicle create(@RequestBody VehicleDto vehicleDto) throws JSONException, IOException {
        return vehicleService.create(vehicleDto);
    }

    @PreAuthorize("hasAuthority('TRANS_VEHICLE_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(vehicleService.delete(id));
    }

    @GetMapping(value = "/list")
    public List<Vehicle> findAll(){
        return vehicleService.findAll();
    }




}
