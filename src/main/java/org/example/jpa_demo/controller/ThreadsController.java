package org.example.jpa_demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.jpa_demo.entity.Threads;
import org.example.jpa_demo.event.ThreadsEventPublisher;
import org.example.jpa_demo.service.ThreadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ThreadsController {

    private static final Log logger = LogFactory.getLog(ThreadsController.class);

    private final ThreadsService threadsService;
    private final ThreadsEventPublisher threadsEventPublisher;

    @Autowired
    public ThreadsController(ThreadsService threadsService, ThreadsEventPublisher threadsEventPublisher) {
        this.threadsService = threadsService;
        this.threadsEventPublisher = threadsEventPublisher;
    }

    /*
        @GetMapping("/ticketlist/{userId}")
        public ResponseEntity<List<Ticket>> getTicketList(@PathVariable String userId) {
            logger.info("getTicketList start");
            List<Ticket> ticketList = ticketService.getTicketsByUserId(userId);
            return new ResponseEntity<>(ticketList, HpStatus.OK);
        }/**/
    @GetMapping("/threads/{ticketId}")
    public ResponseEntity<List<Threads>> getThreadsByTicketId(@PathVariable String ticketId) {
        logger.info("getTicketList start");
        List<Threads> threadsList = threadsService.getThreadsByTicketID(ticketId);
        return new ResponseEntity<>(threadsList, HttpStatus.OK);
    }

    @PostMapping("/addThreads")
    public String addTicket(@RequestBody Map<String, Object> requestBody) {
        int ticketID = Integer.parseInt(requestBody.get("ticketID").toString());
        int userID = Integer.parseInt(requestBody.get("userID").toString());

        String ticketType = (String) requestBody.get("ticketType");
        String message = (String) requestBody.get("message");
        // Process the ticket data and media files
        // Save the ticket details and media files to a database or storage
        // ticketService.createTicket(ticketType,subject,ticketDetails);

        logger.info("ticketID = " + ticketID);
        logger.info("userID = " + userID);
        logger.info("ticketType = " + ticketType);
        logger.info("message = " + message);

        Threads threads = threadsService.createThreads(ticketID, userID, ticketType, message);
        threadsEventPublisher.publishThreadsCreatedEvent(threads);
        // Return a response indicating success or failure
        logger.info("new threads" + threads);
        return "threads added successfully";
    }


}