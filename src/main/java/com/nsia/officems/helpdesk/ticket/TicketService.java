package com.nsia.officems.helpdesk.ticket;

import java.util.List;
import java.util.Map;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.helpdesk.ticket.dto.TicketDto;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface TicketService {
    public Ticket save(Ticket obj);
    public Object getList(DataTablesInput input, Map<String, String> filters);
    // public List<Ticket> findByTicketId(Long ticketboardId);
    public Ticket findById(Long id);
    public boolean delete(long id);
    public Ticket create(Ticket ticket);
    public Ticket create(TicketDto ticketDto);
}
