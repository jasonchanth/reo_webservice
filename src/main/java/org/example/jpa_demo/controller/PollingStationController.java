package org.example.jpa_demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.jpa_demo.entity.PollingStation;
import org.example.jpa_demo.entity.PollingStationTask;
import org.example.jpa_demo.entity.PollingStationSchedule;
import org.example.jpa_demo.entity.User;
import org.example.jpa_demo.service.PollingStationService;
import org.example.jpa_demo.service.UserMappingService;
import org.example.jpa_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PollingStationController {

    private static final Log logger = LogFactory.getLog(PollingStationController.class);

    private final PollingStationService pollingStationService;
    private final UserMappingService userMappingService;
    private final UserService userService;

    @Autowired
    public PollingStationController(PollingStationService pollingStationService, UserMappingService userMappingService, UserService userService) {
        this.pollingStationService = pollingStationService;
        this.userMappingService = userMappingService;
        this.userService = userService;
    }


    @GetMapping("/pollingstationlist/{userid}")
    public ResponseEntity<List<PollingStation>> getPollingStationsByUserID(@PathVariable String userid) {
        logger.info("get Polling Station list start");
        List<PollingStation> pollingStationsList = pollingStationService.getPollingStationsByUserId(userid);
        return new ResponseEntity<>(pollingStationsList, HttpStatus.OK);
    }
    @GetMapping("/task/{pollingStationId}")
    public ResponseEntity<List<PollingStationTask>> getTaskByPollingStationId(@PathVariable String pollingStationId) {
        logger.info("get Task start");
        List<PollingStationTask> TaskList = pollingStationService.getTaskByPollingStationID(pollingStationId);
        return new ResponseEntity<>(TaskList, HttpStatus.OK);
    }
    @GetMapping("/schedule/{pollingStationId}")
    public ResponseEntity<List<PollingStationSchedule>> getScheduleByPollingStationId(@PathVariable String pollingStationId) {
        logger.info("get Schedule start");
        List<PollingStationSchedule> TaskList = pollingStationService.getScheduleByPollingStationID(pollingStationId);
        return new ResponseEntity<>(TaskList, HttpStatus.OK);
    }
    @GetMapping("/scheduledpollingstationlist/{userid}")
    public ResponseEntity<List<PollingStation>> getScheduledPollingStationsByUserID(@PathVariable String userid) {
        logger.info("get ScheduledPolling Station list start");
        List<PollingStation> scheduledpollingStationsList = new ArrayList<>();
        List<PollingStation> pollingStationsList = new ArrayList<>();
        //List<PollingStation> pollingStationsList = pollingStationService.getPollingStationsByUserId(userid);
        User user = userService.getUserById(Integer.parseInt(userid));
        List<User> userList = userMappingService.getUserMappingList(user);
        logger.info("ScheduledPolling userList size "+userList.size());

        for(User u : userList) {
            pollingStationsList.addAll(pollingStationService.getPollingStationsByUserId(u.getId()+""));
        }
        logger.info("ScheduledPolling polling station list size "+pollingStationsList.size());
        for(PollingStation ps:pollingStationsList){
            logger.info("ScheduledPolling polling station id "+ps.getId());
            List<PollingStationSchedule> scheduleList = pollingStationService.getScheduleByPollingStationID(ps.getId());
            logger.info("ScheduledPolling schedule list size "+scheduleList.size());
            if(!scheduleList.isEmpty() ){
                for(PollingStationSchedule s:scheduleList){
                    if(s.getLast_update() != null){
                        scheduledpollingStationsList.add(ps);
                    }
                }

            }
        }

        return new ResponseEntity<>(scheduledpollingStationsList, HttpStatus.OK);
    }
    @GetMapping("/nonscheduledpollingstationlist/{userid}")
    public ResponseEntity<List<PollingStation>> getNonScheduledPollingStationsByUserID(@PathVariable String userid) {
        logger.info("get Non ScheduledPolling Station list start");
        List<PollingStation> nonScheduledpollingStationsList = new ArrayList<>();
        List<PollingStation> pollingStationsList = new ArrayList<>();
        User user = userService.getUserById(Integer.parseInt(userid));
        List<User> userList = userMappingService.getUserMappingList(user);
        logger.info("NonScheduledPolling userList size "+userList.size());
        for(User u : userList) {
            pollingStationsList.addAll(pollingStationService.getPollingStationsByUserId(u.getId()+""));
        }
        logger.info("Non ScheduledPolling polling station list size "+pollingStationsList.size());
        //List<PollingStation> pollingStationsList = pollingStationService.getPollingStationsByUserId(userid);
        for(PollingStation ps:pollingStationsList){
            logger.info("Non ScheduledPolling polling station id "+ps.getId());
            List<PollingStationSchedule> scheduleList = pollingStationService.getScheduleByPollingStationID(ps.getId());
            logger.info("Non ScheduledPolling schedule list size "+scheduleList.size());
            if(!scheduleList.isEmpty() ){
                for(PollingStationSchedule s:scheduleList){
                    if(s.getLast_update() == null){
                        nonScheduledpollingStationsList.add(ps);
                    }
                }

            }
        }

        return new ResponseEntity<>(nonScheduledpollingStationsList, HttpStatus.OK);
    }

}