package org.example.jpa_demo.service;

import org.example.jpa_demo.entity.Ticket;
import org.example.jpa_demo.entity.User;
import org.example.jpa_demo.repository.TicketRepository;
import org.example.jpa_demo.repository.UserMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserMappingRepository userMappingRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, UserMappingRepository userMappingRepository) {
        this.ticketRepository = ticketRepository;
        this.userMappingRepository = userMappingRepository;
    }

    @Override
    public List<Ticket> findRecentlyUpdatedTickets() {
        return ticketRepository.findRecentlyUpdatedTickets();
    }

    @Override
    public List<Ticket> getTicketsByUserId(String userId,String role) {
        if(role.equals("APROIT")){
            return ticketRepository.getTicketsByCreatedByUserId(userId);
        }
        return ticketRepository.getTicketsByAssignedToUserId(userId);
    }

    @Override
    public Ticket createTicket(String type, String subject, String details, String userID) {
        int createdBy = Integer.parseInt(userID);
        User assignedToUser = userMappingRepository.findSTUsers(Integer.parseInt(userID)).get(0);
        System.out.println(assignedToUser.getUsername());



        return ticketRepository.createTicket(type, subject, details,createdBy , assignedToUser.getId() );
    }

    @Override
    public Ticket getTicketByThreads(String ticketId) {
        return ticketRepository.findTicketByTicketId(ticketId);
    }

    @Override
    public Ticket closeTicket(int ticketID, int userID) {
        return ticketRepository.closeTicket(ticketID);
    }
}
