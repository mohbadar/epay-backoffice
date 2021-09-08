package com.nsia.officems.helpdesk.ticket;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>  {
    // List<Ticket> findByTicketId(Long ticketId);
}