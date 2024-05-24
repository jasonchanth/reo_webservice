package org.example.jpa_demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.jpa_demo.entity.Alert;
import org.example.jpa_demo.entity.PollingStation;
import org.example.jpa_demo.entity.PollingStationTask;
import org.example.jpa_demo.entity.Ticket;
import org.example.jpa_demo.service.AlertService;
import org.example.jpa_demo.service.PollingStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class AlertController {

    private static final Log logger = LogFactory.getLog(PollingStationController.class);

    private final AlertService alertService;

    @Autowired
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }


    @GetMapping("/alerts/{PSID}")
    public ResponseEntity<List<Alert>> getAlertsByPSID(@PathVariable String PSID) {
        logger.info("get alerts start");
        List<Alert> alertList = alertService.getAlertsByPSID(PSID);
        return new ResponseEntity<>(alertList, HttpStatus.OK);
    }
    @PostMapping("/updateAlert")
    public String addTicket(
            @RequestParam("alertid") String alertid,
            @RequestParam("answer") String answer,
            @RequestParam("PS") String PS,
            @RequestParam(value = "mediaFiles", required = false) List<MultipartFile> mediaFiles) {
        logger.info("updateAlert start");
        // Process the ticket data and media files


        // Save the ticket details and media files to a database or storage
        // ticketService.createTicket(ticketType,subject,ticketDetails);

            String status = alertService.updateAlert(alertid, PS, answer);
       // ticketEventPublisher.publishTicketCreatedEvent(ticket);
        // Return a response indicating success or failure

        return status;
    }


}