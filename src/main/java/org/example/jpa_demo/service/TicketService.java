package org.example.jpa_demo.service;

import org.example.jpa_demo.entity.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> findRecentlyUpdatedTickets();

    List<Ticket> getTicketsByUserId(String userId,String role);

    Ticket createTicket(String type, String subject, String details, String userID);

    Ticket getTicketByThreads(String ticketId);

    Ticket closeTicket(int ticketID, int userID);
}