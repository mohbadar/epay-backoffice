package com.nsia.officems.helpdesk.ticket.dto;

import com.nsia.officems.doc_mng.document.Document;
import com.nsia.officems.helpdesk.service_type.ServiceType;
import com.nsia.officems.helpdesk.service_type.ServiceTypeService;
import com.nsia.officems.helpdesk.ticket.Ticket;

public class TicketMapper {

    public static Ticket map(TicketDto dto, Ticket ticket, ServiceTypeService serviceTypeService)
    {
        if(ticket == null)
            ticket = new Ticket();
        
        ticket.setTitle(dto.getTitle());
        ticket.setDescription(dto.getDescription());
        ticket.setServiceType(serviceTypeService.findById(dto.getServiceTypeId()));
        return ticket;

    }
}
