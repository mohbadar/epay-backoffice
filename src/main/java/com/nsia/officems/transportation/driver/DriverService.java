package com.nsia.officems.transportation.driver;

import com.nsia.officems.transportation.driver.dto.DriverDto;
import com.nsia.officems.transportation.request.Request;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

import java.util.List;
import java.util.Map;

public interface DriverService {

    public Driver save(Driver obj);
    public Object getList(DataTablesInput input, Map<String, String> filters);
    public Driver findById(Long id);
    public boolean delete(long id);
    public Driver create(Driver driver);
    public Driver create(DriverDto driverDto);
    public Boolean update(Long id, DriverDto dto);
    public List<Driver> findAll();

}
