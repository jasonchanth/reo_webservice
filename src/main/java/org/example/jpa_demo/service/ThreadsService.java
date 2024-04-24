package org.example.jpa_demo.service;

import org.example.jpa_demo.entity.Threads;

import java.util.List;

public interface ThreadsService {


    Threads createThreads(int ticketid, int userid, String type, String message);

    List<Threads> getThreadsByTicketID(String ticketID);

}