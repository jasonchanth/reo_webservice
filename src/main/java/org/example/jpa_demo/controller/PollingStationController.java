package org.example.jpa_demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.jpa_demo.entity.PollingStation;
import org.example.jpa_demo.service.PollingStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PollingStationController {

    private static final Log logger = LogFactory.getLog(PollingStationController.class);

    private final PollingStationService pollingStationService;

    @Autowired
    public PollingStationController(PollingStationService pollingStationService) {
        this.pollingStationService = pollingStationService;
    }


    @GetMapping("/pollingstationlist/{userid}")
    public ResponseEntity<List<PollingStation>> getPollingStationsByUserID(@PathVariable String userid) {
        logger.info("get Polling Station list start");
        List<PollingStation> pollingStationsList = pollingStationService.getPollingStationsByUserId(userid);
        return new ResponseEntity<>(pollingStationsList, HttpStatus.OK);
    }

}