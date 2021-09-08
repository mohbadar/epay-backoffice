package com.nsia.officems.transportation.driver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.transportation.driver.dto.DriverDto;
import com.nsia.officems.transportation.request.Request;
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
@RequestMapping("/api/transport/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @PreAuthorize("hasAuthority('TRANS_DRIVER_LIST')")
    @PostMapping("/list")
    public Object getRequestList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside getRequestList " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return driverService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @PreAuthorize("hasAuthority('TRANS_DRIVER_VIEW')")
    @GetMapping(value = "/{driverId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "driverId", required = true) long id) {
        System.out.println("driver " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        Driver driver = driverService.findById(id);
        data.put("driver", driver);
        return ResponseEntity.ok(data);
    }

    @PreAuthorize("hasAuthority('TRANS_DRIVER_EDIT')")
    @PutMapping(value = "/{id}/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestBody String data, HttpServletRequest request) throws Exception {
        Gson g = new Gson();
        DriverDto dto = g.fromJson(data, DriverDto.class);
        return ResponseEntity.ok(driverService.update(id, dto));

    }

    @PreAuthorize("hasAuthority('TRANS_DRIVER_CREATE')")
    @PostMapping()
    public Driver create(@RequestBody DriverDto driverDto) throws JSONException, IOException {
        return driverService.create(driverDto);
    }

    @PreAuthorize("hasAuthority('TRANS_DRIVER_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(driverService.delete(id));
    }

    @GetMapping(value = "/list")
    public List<Driver> findAll(){
        return driverService.findAll();
    }

}
