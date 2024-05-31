package org.example.jpa_demo.repository;

import org.example.jpa_demo.entity.Ticket;

import java.util.List;

public interface TicketRepository {
    List<Ticket> findRecentlyUpdatedTickets();

    List<Ticket> getTicketsByCreatedByUserId(String userId);

    List<Ticket> getTicketsByAssignedToUserId(String userId);

    Ticket createTicket(String Type, String subject, String details, int createdBy, int assignedTo);

    Ticket findTicketByTicketId(String ticketId);

    Ticket closeTicket(int ticketID);
}
