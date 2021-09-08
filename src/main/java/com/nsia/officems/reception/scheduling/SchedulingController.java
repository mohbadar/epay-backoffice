package com.nsia.officems.reception.scheduling;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/receptions/scheduling")
public class SchedulingController {

    @Autowired
    private SchedulingService schedulingService;

    @Autowired
    private ObjectMapper objectMapper;

    String datePattern = "yyyy-MM-dd";
    DateFormat df = new SimpleDateFormat(datePattern);

    @PreAuthorize("hasAuthority('RECEP_VISIT_SCHEDULE_LIST')")
    @PostMapping("/visits/list")
    public Object getScheduleVisitList(@RequestBody String requestBody) throws Exception {
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
            return schedulingService.getVisitList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @PreAuthorize("hasAuthority('RECEP_VISITOR_SCHEDULE_LIST')")
    @PostMapping("/visitors/list/{interval}")
    public Object getScheduleVisitorList(@RequestBody String requestBody, @PathVariable(name = "interval", required = true) String interval) throws Exception {
        Date today = Calendar.getInstance().getTime();
        String todayAsString = this.df.format(today);
        System.out.println(todayAsString);

        String whereCondition = "";
        if(interval.equals("today")) {
            whereCondition = " rvvor.status='SCHEDULED' and date(v.visit_date)='" + todayAsString + "' ";
        } else if(interval.equals("previous")) {
            whereCondition = " rvvor.status='SCHEDULED' and date(v.visit_date)<'" + todayAsString + "' ";
        } else if(interval.equals("upcoming")) {
            whereCondition = " rvvor.status='SCHEDULED' and date(v.visit_date)>'" + todayAsString + "' ";
        } else if(interval.equals("all")) {
            whereCondition = "";
        } else {
            return null;
        }

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
            return schedulingService.getVisitorList(input, filters, whereCondition);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @PreAuthorize("hasAuthority('RECEP_VEHICLE_SCHEDULE_LIST')")
    @PostMapping("/vehicles/list/{interval}")
    public Object getScheduleVehicleList(@RequestBody String requestBody, @PathVariable(name = "interval", required = true) String interval) throws Exception {
        Date today = Calendar.getInstance().getTime();
        String todayAsString = this.df.format(today);
        System.out.println(todayAsString);

        String whereCondition = "";
        if(interval.equals("today")) {
            whereCondition = " rvveh.status='SCHEDULED' and date(v.visit_date)='" + todayAsString + "' ";
        } else if(interval.equals("previous")) {
            whereCondition = " rvveh.status='SCHEDULED' and date(v.visit_date)<'" + todayAsString + "' ";
        } else if(interval.equals("upcoming")) {
            whereCondition = " rvveh.status='SCHEDULED' and date(v.visit_date)>'" + todayAsString + "' ";
        } else if(interval.equals("all")) {
            whereCondition = "";
        } else {
            return null;
        }

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
            return schedulingService.getVehicleList(input, filters, whereCondition);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }
}