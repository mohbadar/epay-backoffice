package com.nsia.officems.reception.visitor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface VisitorService {
    public Visitor save(Visitor visitor);
    public List<Visitor> saveAll(List<Visitor> visitors);
    public Visitor processVisitor(Long visitId, Long visitorId, String data) throws JSONException, IOException ;
    public Object getList(DataTablesInput input, Map<String, String> filters);
    public Visitor findById(Long id);
    public List<Visitor> findByVisitId(Long visitId);
    public boolean delete(long id);
    public Visitor create(Visitor visitor, boolean isSchedule) throws JSONException, IOException;
    public Visitor setAttributes(Visitor visitor, JSONObject visitorJson) throws JSONException, IOException;
    public long countALL();
    public long countToday();
}
