package com.nsia.officems.transportation.vehicle;
import com.nsia.officems.transportation.driver.Driver;
import com.nsia.officems.transportation.vehicle.dto.VehicleDto;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

import java.util.List;
import java.util.Map;

public interface VehicleService {

    public Vehicle save(Vehicle obj);
    public Object getList(DataTablesInput input, Map<String, String> filters);
    public Vehicle findById(Long id);
    public boolean delete(long id);
    public Vehicle create(Vehicle vehicle);
    public Vehicle create(VehicleDto vehicleDto);
    public Boolean update(Long id, VehicleDto dto);
    public List<Vehicle> findAll();
}
