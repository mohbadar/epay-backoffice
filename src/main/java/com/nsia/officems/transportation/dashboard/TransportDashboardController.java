package com.nsia.officems.transportation.dashboard;

import com.nsia.officems.transportation.request.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transport/dashboard")
public class TransportDashboardController {

    @Autowired
    private RequestService requestService;

    @GetMapping(value = "/count")
    public ResponseEntity<Map<String, Object>> countData() {
        Map<String, Object> data = new HashMap<String, Object>();
        long allRequests = requestService.countALL();
        long todayRequests = requestService.countToday();
        data.put("allrequests", allRequests);
        data.put("todayrequests", todayRequests);
        return ResponseEntity.ok(data);
    }

    @GetMapping(path = { "/department" })
    public List getRequestCountByDepartment() {
        return requestService.getRequestCountByDepartment();
    }

    @GetMapping(path = { "/status" })
    public List getRequestCountByStatus() {
        return requestService.getRequestCountByStatus();
    }


}
