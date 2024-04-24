package org.example.jpa_demo.repository;

import org.example.jpa_demo.entity.Threads;
import org.example.jpa_demo.entity.Ticket;

import java.util.List;

public interface TicketRepository {
    List<Ticket> findRecentlyUpdatedTickets();

    List<Ticket> getTicketsByUserId();

    Ticket createTicket(String Type, String subject, String details);

    Ticket findTicketByThreads(Threads threads);
}
