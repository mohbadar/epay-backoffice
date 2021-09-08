package com.nsia.officems.helpdesk.service_type;

import java.util.List;

import com.nsia.officems.helpdesk.service_type.dto.ServiceTypeDto;

public interface ServiceTypeService {
    public ServiceType save(ServiceType visit);
    public List<ServiceType> findAll();
    public ServiceType findById(Long id);
    public ServiceType create(ServiceType serviceType);
    public ServiceType create(ServiceTypeDto serviceTypeDto);
    public Boolean delete(Long id);
    public boolean update(Long id, ServiceType serviceType);
}
