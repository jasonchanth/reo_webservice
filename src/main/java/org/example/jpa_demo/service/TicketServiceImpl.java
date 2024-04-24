package org.example.jpa_demo.service;

import org.example.jpa_demo.entity.Threads;
import org.example.jpa_demo.entity.Ticket;
import org.example.jpa_demo.entity.User;
import org.example.jpa_demo.repository.TicketRepository;
import org.example.jpa_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> findRecentlyUpdatedTickets() {
        return ticketRepository.findRecentlyUpdatedTickets();
    }

    @Override
    public List<Ticket> getTicketsByUserId(String userId) {
        return ticketRepository.getTicketsByUserId();
    }

    @Override
    public Ticket createTicket(String type, String subject, String details) {
        return ticketRepository.createTicket(type, subject, details);
    }

    @Override
    public Ticket getTicketByThreads(Threads threads) {
        return ticketRepository.findTicketByThreads(threads);
    }
}
