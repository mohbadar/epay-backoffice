package com.nsia.officems.reception.scheduling;

import java.util.Map;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface SchedulingService {
    public Object getVisitList(DataTablesInput input, Map<String, String> filters);
    public Object getVisitorList(DataTablesInput input, Map<String, String> filters, String whereCondition);
    public Object getVehicleList(DataTablesInput input, Map<String, String> filters, String whereCondition);
}
