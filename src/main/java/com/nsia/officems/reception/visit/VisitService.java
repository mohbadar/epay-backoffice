package com.nsia.officems.reception.visit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.reception.visit.dto.VisitDto;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.web.multipart.MultipartFile;

public interface VisitService {
    public Visit save(Visit visit);
    public Object getList(DataTablesInput input, Map<String, String> filters);
    public Object getMyList(DataTablesInput input, Map<String, String> filters);
    public Visit findById(Long id);
    public boolean delete(long id);
    public Visit create(String data, User loggedInUser, boolean isSchedule) throws JSONException, IOException;
    public Visit createSchedule(String data, User loggedInUser) throws JSONException, IOException;
    public Visit setAttributes(Visit visit, JSONObject visitJson) throws JSONException, IOException;
    public long countALL();
    public long countToday();
    public List getVisitCountByDepartment();
    public List getVisitCountByType();
    public VisitDto findById_Dto(Long id);

    public List<List<String>> readExcelAsJson(MultipartFile file) throws IOException;
    public File getExcelTemplate() throws IOException;
}
