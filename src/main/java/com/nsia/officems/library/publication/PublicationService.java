package com.nsia.officems.library.publication;

import java.io.IOException;
import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface PublicationService {
    public Publication save(Publication obj);
    public Object getList(DataTablesInput input, Map<String, String> filters);
    public Publication findById(Long id);
    public boolean delete(long id);
    public Publication create(String data) throws JSONException, IOException;
}
