package com.nsia.officems.transportation.driver.impl;

import com.nsia.officems._identity.authentication.user.UserRepository;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.transportation.driver.Driver;
import com.nsia.officems.transportation.driver.DriverRepository;
import com.nsia.officems.transportation.driver.DriverService;
import com.nsia.officems.transportation.driver.dto.DriverDto;
import com.nsia.officems.transportation.driver.dto.DriverMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Driver save(Driver obj) {
        return driverRepository.save(obj);
    }

    @Override
    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " inner join public.user_tbl usr on usr.id=dr.created_by ";
        // To have first AND with no error
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.transport_driver dr", null, joinClause, whereClause, groupByClause, input);
    }
    @Override
    public Driver findById(Long id) {
        System.out.println("Driver.findById()" + id);
        Optional<Driver> driver = driverRepository.findById(id);
        if (driver.isPresent()) {
            System.out.println("Request: " + driver);
            return driver.get();
        }
        return null;
    }

    @Override
    public boolean delete(long id) {
        Optional<Driver> driver = driverRepository.findById(id);
        if (driver.isPresent()) {
            Driver info = driver.get();
            info.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            driverRepository.save(info);
            return true;
        }
        return false;
    }

    @Override
    public Driver create(Driver driver) {

        return save(driver);
    }

    @Override
    public Driver create(DriverDto driverDto) {
        Driver driver = DriverMapper.map(driverDto, new Driver());
        //driver.setCreatedBy(userService.getLoggedInUser());
        driver.setCreatedBy(userRepository.findById(1l).orElse(null));
        return create(driver);
    }

    @Override
    public Boolean update(Long id, DriverDto dto) {
        Optional<Driver> driver = driverRepository.findById(id);
        if (driver.isPresent()) {
            Driver info = DriverMapper.map(dto, driver.get());
            if (!info.equals(null)) {
                driverRepository.save(info);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Driver> findAll() {
        return driverRepository.findAll();
    }
}
