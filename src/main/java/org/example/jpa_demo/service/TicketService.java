package org.example.jpa_demo.service;

import org.example.jpa_demo.entity.Threads;
import org.example.jpa_demo.entity.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> findRecentlyUpdatedTickets();

    List<Ticket> getTicketsByUserId(String userId);

    Ticket createTicket(String type, String subject, String details);

    Ticket getTicketByThreads(Threads threads);
}