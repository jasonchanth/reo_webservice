package org.example.jpa_demo.repository;

import org.example.jpa_demo.entity.Threads;
import org.example.jpa_demo.entity.Ticket;

import java.util.List;

public interface ThreadsRepository {


    Threads createThreads(int ticketid, int userid, String type, String message);

    List<Threads> getThreadsByTicketID(String ticketID);
}
