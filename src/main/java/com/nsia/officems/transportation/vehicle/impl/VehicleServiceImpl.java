package com.nsia.officems.transportation.vehicle.impl;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.transportation.vehicle.Vehicle;
import com.nsia.officems.transportation.vehicle.VehicleService;
import com.nsia.officems.transportation.vehicle.dto.VehicleDto;
import com.nsia.officems.transportation.vehicle.VehicleRepository;
import com.nsia.officems.transportation.vehicle.dto.VehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserService userService;


    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Override
    public Vehicle save(Vehicle obj) {
        return vehicleRepository.save(obj);
    }

    @Override
    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " inner join public.user_tbl usr on usr.id=veh.created_by ";
        // To have first AND with no error
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.transport_vehicle veh", null, joinClause, whereClause, groupByClause, input);
    }

    @Override
    public Vehicle findById(Long id) {

        System.out.println("Vehicle.findById()" + id);
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            System.out.println("Vehicle: " + vehicle);
            return vehicle.get();
        }
        return null;
    }

    @Override
    public boolean delete(long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            Vehicle info = vehicle.get();
            info.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            vehicleRepository.save(info);
            return true;
        }
        return false;
    }

    @Override
    public Vehicle create(Vehicle vehicle) {

        return save(vehicle);
    }

    @Override
    public Vehicle create(VehicleDto vehicleDto) {
        Vehicle vehicle = VehicleMapper.map(vehicleDto, new Vehicle());
        vehicle.setCreatedBy(userService.getLoggedInUser());
        return create(vehicle);
    }

    @Override
    public Boolean update(Long id, VehicleDto dto) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            Vehicle info = VehicleMapper.map(dto, vehicle.get());
            if (!info.equals(null)) {
                vehicleRepository.save(info);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }
}
