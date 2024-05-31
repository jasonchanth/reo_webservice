package org.example.jpa_demo.service;

import org.example.jpa_demo.entity.Threads;
import org.example.jpa_demo.repository.ThreadsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreadsServiceImpl implements ThreadsService {

    private final ThreadsRepository threadsRepository;

    @Autowired
    public ThreadsServiceImpl(ThreadsRepository threadsRepository) {
        this.threadsRepository = threadsRepository;
    }

    @Override
    public Threads createThreads(int ticketid, int userid, String type, String message, String filePath) {
        return threadsRepository.createThreads(ticketid, userid, type, message, filePath);
    }

    @Override
    public List<Threads> getThreadsByTicketID(String ticketID) {
        return threadsRepository.getThreadsByTicketID(ticketID);
    }
}
