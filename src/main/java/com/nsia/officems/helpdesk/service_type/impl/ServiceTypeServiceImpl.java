package com.nsia.officems.helpdesk.service_type.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.helpdesk.service_type.ServiceType;
import com.nsia.officems.helpdesk.service_type.ServiceTypeRepository;
import com.nsia.officems.helpdesk.service_type.ServiceTypeService;
import com.nsia.officems.helpdesk.service_type.dto.ServiceTypeDto;
import com.nsia.officems.helpdesk.service_type.dto.ServiceTypeMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {
    private final ServiceTypeRepository serviceTypeRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private DataTablesUtil dataTablesUtil;

    public ServiceTypeServiceImpl(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
    }

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " ";
        // To have first AND with no error
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.recep_visitor vor", null, joinClause, whereClause, groupByClause, input);
    }

    public ServiceType findById(Long id) {
        System.out.println(" ServiceType.findById()" + id);
        Optional<ServiceType> serviceType = serviceTypeRepository.findById(id);
        if (serviceType.isPresent()) {
            System.out.println("ServiceType: " + serviceType);
            return serviceType.get();
        }
        return null;
    }

    public boolean delete(long id) {
        Optional<ServiceType> serviceType = serviceTypeRepository.findById(id);
        if (serviceType.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public List<ServiceType> findAll() {
        return serviceTypeRepository.findAll();
    }

    @Override
    public ServiceType save(ServiceType serviceType) {
        return serviceTypeRepository.save(serviceType);
    }

    @Override
    public ServiceType create(ServiceType serviceType) {
        return save(serviceType);
    }

    @Override
    public ServiceType create(ServiceTypeDto serviceTypeDto) {
        ServiceType serviceType = ServiceTypeMapper.map(serviceTypeDto, new ServiceType());
        serviceType.setCreatedBy(userService.getLoggedInUser());
        return create(serviceType);
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public boolean update(Long id, ServiceType serviceType) {
        return false;
    }


}
