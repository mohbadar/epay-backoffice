package com.nsia.officems.helpdesk.ticket.impl;

import com.nsia.officems.helpdesk.service_type.ServiceTypeService;
import com.nsia.officems.helpdesk.ticket.Ticket;
import com.nsia.officems.helpdesk.ticket.TicketRepository;
import com.nsia.officems.helpdesk.ticket.TicketService;
import com.nsia.officems.helpdesk.ticket.dto.TicketDto;
import com.nsia.officems.helpdesk.ticket.dto.TicketMapper;

import java.util.Map;
import java.util.Optional;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;
/**
 *
 * @author mohbadar
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository  ticketRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ServiceTypeService serviceTypeService;
    
    @Autowired
    private DataTablesUtil dataTablesUtil;

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " inner join public.helpdesk_service_type serv_type on serv_type.id=tic.service_type_id ";
        joinClause += " inner join public.user_tbl usr on usr.id=tic.created_by ";
        // To have first AND with no error
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.helpdesk_ticket tic", null, joinClause, whereClause, groupByClause, input);
    }

    // @Override
    // public List<Ticket> findByTicketId(Long ticketboardId) {
    //     return ticketRepository.findByTicketId(ticketboardId);
    // }
    
    @Override
    public Ticket findById(Long id) {
        System.out.println("Ticket.findById()" + id);
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            System.out.println("Ticket: " + ticket);
            return ticket.get();
        }
        return null;
    }

    public boolean delete(long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public Ticket save(Ticket obj) {
        return this.ticketRepository.save(obj);
    }

    @Override
    public Ticket create(Ticket ticket) {
        return save(ticket);
    }

    @Override
    public Ticket create(TicketDto ticketDto) {
        Ticket ticket = TicketMapper.map(ticketDto, new Ticket(), serviceTypeService);
        ticket.setCreatedBy(userService.getLoggedInUser());
        return create(ticket);
    }
}
