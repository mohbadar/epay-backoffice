package com.nsia.officems.reception.dashboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nsia.officems.reception.visit.VisitService;
import com.nsia.officems.reception.visitor.VisitorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reception/dashboard")
public class dashboardController {

    @Autowired
    private VisitService visitService;
    @Autowired
    private VisitorService visitorService;

    @GetMapping(value = "/count")
    public ResponseEntity<Map<String, Object>> countData() {
      Map<String, Object> data = new HashMap<String, Object>();
      long allVisits = visitService.countALL();
      long todayVisits = visitService.countToday();
      long allvisitors = visitorService.countALL();
      long todayVisitors = visitorService.countToday();
  
      data.put("allvisit", allVisits);
      data.put("todayvisit", todayVisits);
      data.put("allvisitor", allvisitors);
      data.put("todayvisitor", todayVisitors);
      return ResponseEntity.ok(data);
    }

    @GetMapping(path = { "/department" })
    public List getVisitCountByDepartment() {
      return visitService.getVisitCountByDepartment();
    }

    @GetMapping(path = { "/visittype" })
    public List getVisitCountByType() {
      return visitService.getVisitCountByType();
    }
    
}
