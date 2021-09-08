package com.nsia.officems.reception.scheduling.impl;

import com.nsia.officems.reception.scheduling.SchedulingService;

import java.util.Map;

import com.nsia.officems._util.DataTablesUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class SchedulingServiceImpl implements SchedulingService {
    @Value(value = "${app.upload.reception.visitors}")
    private String visitorPhotosPath;

    @Value(value = "${app.upload.reception.vehicles}")
    private String vehiclePhotosPath;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    public Object getVisitList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "inner join public.department d on d.id=v.host_department_id";
        // To have first AND with no error
        String whereClause = " v.status='SCHEDULED' " + dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.recep_visit v", null, joinClause, whereClause, groupByClause, input);
    }

    public Object getVisitorList(DataTablesInput input, Map<String, String> filters, String whereCondition) {
        String joinClause = " inner join recep_visit_visitor rvvor on rvvor.visitor_id=vor.id";
        joinClause += " inner join recep_visit v on v.id=rvvor.visit_id";
        joinClause += " inner join public.department d on d.id=v.host_department_id";
        // To have first AND with no error
        String whereClause =  whereCondition + dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.recep_visitor vor", null, joinClause, whereClause, groupByClause, input);
    }

    public Object getVehicleList(DataTablesInput input, Map<String, String> filters, String whereCondition) {
        String joinClause = " inner join recep_visit_vehicle rvveh on rvveh.vehicle_id=veh.id";
        joinClause += " inner join recep_visit v on v.id=rvveh.visit_id";
        joinClause += " inner join public.department d on d.id=v.host_department_id";
        // To have first AND with no error
        String whereClause = whereCondition + dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.recep_vehicle veh", null, joinClause, whereClause, groupByClause, input);
    }

}
