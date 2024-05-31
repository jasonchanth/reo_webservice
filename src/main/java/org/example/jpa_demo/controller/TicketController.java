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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/ticketlist/{userInfo}")
    public ResponseEntity<List<Ticket>> getTicketList(@PathVariable String userInfo) {
        logger.info("getTicketList start");
        try {
            if (userInfo == null || userInfo.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            String[] userInfoArray = userInfo.split("_");
            String userID = userInfoArray[0];
            String userRole = userInfoArray[1];
            logger.info("userID: " + userID);
            logger.info("userRole: " + userRole);

            List<Ticket> ticketList = ticketService.getTicketsByUserId(userID,userRole);
            return new ResponseEntity<>(ticketList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while retrieving ticket list", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//non use
    @GetMapping("/ticketthreads/{ticketId}")
    public ResponseEntity<List<Ticket>> getThreadsByTicketId(@PathVariable String ticketId) {
        logger.info("getTicketList start");
        List<Ticket> ticketList = ticketService.getTicketsByUserId(ticketId,ticketId);
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }
    @PostMapping("/closeTicket")
    public String closeTicket(
            @RequestBody Map<String, Object> requestBody) {
        logger.info("Close Ticket start");
        int ticketID = Integer.parseInt(requestBody.get("ticketID").toString());
        int userID = Integer.parseInt(requestBody.get("userID").toString());


        Ticket ticket = ticketService.closeTicket(ticketID, userID);
        //ticketEventPublisher.publishTicketCreatedEvent(ticket);
        // Return a response indicating success or failure
        logger.info(" Ticket status" + ticket.getStatus_name());
        return "Ticket closed successfully";
    }

    @PostMapping("/addTicket")
    public String addTicket(
            @RequestParam("ticketType") String ticketType,
            @RequestParam("subject") String subject,
            @RequestParam("ticketDetails") String ticketDetails,
            @RequestParam("userID") String userID,
            @RequestParam(value = "mediaFiles", required = false) List<MultipartFile> mediaFiles) {
        logger.info("addTicket start");
        // Process the ticket data and media files
        if (mediaFiles != null) {
            for (MultipartFile file : mediaFiles) {
                logger.info("add file" + file.getOriginalFilename());

                // Process the media file here
                //save file to server or cloud storage
                // Example: saveFile(file);
                saveFile(file);
                // Save the media file to the server
                // You can write code here to save the media file to a specific location
                // Example: file.transferTo(new File("path/to/save/" + file.getOriginalFilename()));
            }
        }
        // Save the ticket details and media files to a database or storage
        // ticketService.createTicket(ticketType,subject,ticketDetails);

        Ticket ticket = ticketService.createTicket(ticketType, subject, ticketDetails, userID);
        ticketEventPublisher.publishTicketCreatedEvent(ticket);
        // Return a response indicating success or failure
        logger.info("new Ticket" + ticket);
        return "Ticket added successfully";
    }

    private void saveFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            byte[] bytes = file.getBytes();
            // Example: save to the server
            //String serverPath = "/path/to/linux/server/directory/" + fileName;
            // write the bytes to the server path

            // If running on Windows, the file path should be modified as below:
             String serverPath = "D:\\media\\" + fileName;
            // write the bytes to the server path
            logger.info("Save File to " + serverPath);

            Path path = Paths.get(serverPath);
            Files.write(path, bytes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}