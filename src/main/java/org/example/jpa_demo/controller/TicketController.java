package org.example.jpa_demo.controller;

import org.example.jpa_demo.entity.Ticket;
import org.example.jpa_demo.event.TicketEventPublisher;
import org.example.jpa_demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

@RestController
public class TicketController {

    private static final Log logger = LogFactory.getLog(TicketController.class);

    private final TicketService ticketService;
    private final TicketEventPublisher ticketEventPublisher;

    @Autowired
    public TicketController(TicketService ticketService, TicketEventPublisher ticketEventPublisher) {
        this.ticketService = ticketService;
        this.ticketEventPublisher = ticketEventPublisher;
    }

    @GetMapping("/ticketlist/{userId}")
    public ResponseEntity<List<Ticket>> getTicketList(@PathVariable String userId) {
        logger.info("getTicketList start");
        try {
            if (userId == null || userId.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            List<Ticket> ticketList = ticketService.getTicketsByUserId(userId);
            return new ResponseEntity<>(ticketList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while retrieving ticket list", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/ticketthreads/{ticketId}")
    public ResponseEntity<List<Ticket>> getThreadsByTicketId(@PathVariable String ticketId) {
        logger.info("getTicketList start");
        List<Ticket> ticketList = ticketService.getTicketsByUserId(ticketId);
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

    @PostMapping("/addTicket")
    public String addTicket(
            @RequestParam("ticketType") String ticketType,
            @RequestParam("subject") String subject,
            @RequestParam("ticketDetails") String ticketDetails,
            @RequestParam(value = "mediaFiles", required = false) List<MultipartFile> mediaFiles) {
        logger.info("addTicket start");
        // Process the ticket data and media files
        // Save the ticket details and media files to a database or storage
        // ticketService.createTicket(ticketType,subject,ticketDetails);

        Ticket ticket = ticketService.createTicket(ticketType, subject, ticketDetails);
        ticketEventPublisher.publishTicketCreatedEvent(ticket);
        // Return a response indicating success or failure
        logger.info("new Ticket" + ticket);
        return "Ticket added successfully";
    }


}