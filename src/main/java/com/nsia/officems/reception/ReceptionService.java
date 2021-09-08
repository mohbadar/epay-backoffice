package com.nsia.officems.reception;

import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface ReceptionService {
    public Reception save(Reception obj);
    public Object getList(DataTablesInput input, Map<String, String> filters);
    public Reception findById(Long id);
    public boolean delete(long id);
    public Long create(String data) throws JSONException;
}
