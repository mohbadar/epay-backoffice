package com.nsia.officems.helpdesk.service_type.dto;

import com.nsia.officems.helpdesk.service_type.ServiceType;

public class ServiceTypeMapper {

    public static ServiceType map(ServiceTypeDto dto, ServiceType serviceType)
    {
        if(serviceType == null)
        serviceType = new ServiceType();

        serviceType.setName(dto.getName());
        return serviceType;

    }
}
