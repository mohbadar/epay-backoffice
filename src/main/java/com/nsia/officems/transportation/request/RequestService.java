package com.nsia.officems.transportation.request;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.reception.visitor.Visitor;
import com.nsia.officems.transportation.request.dto.RequestDto;
import com.nsia.officems.transportation.request.dto.RequestPrintDto;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface RequestService {
    public Request save(Request visit);
    public Object getList(DataTablesInput input, Map<String, String> filters);
    public Object getPendingList(DataTablesInput input, Map<String, String> filters);
    public Object getClosedList(DataTablesInput input, Map<String, String> filters);
    public Object getMyList(DataTablesInput input, Map<String, String> filters);
    public Request findById(Long id);
    public Request create(RequestDto requestDto, User loggedInUser) throws JSONException, IOException;
    public Request cancelRequest(Long id);
    public Request processRequest(Long id, RequestDto requestDto);
    public boolean delete(long id);
    // public Request create(Request request);
    // public RequestDto create(RequestDto requestDto);
    public Boolean update(Long id, RequestDto dto);
    public List<Request> findAll();
    public Request createRequest(Request request) throws JSONException, IOException;
    public RequestPrintDto findRequestPrintDtoById(Long id);

    public long countALL();
    public long countToday();
    public List getRequestCountByDepartment();
    public List getRequestCountByStatus();


}


