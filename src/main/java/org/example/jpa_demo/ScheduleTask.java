package org.example.jpa_demo;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.example.jpa_demo.entity.PushNotificationPayload;
import org.example.jpa_demo.entity.Ticket;
import org.example.jpa_demo.service.TicketService;
import org.example.jpa_demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ScheduleTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTask.class);
    private final TicketService ticketServiceService;
    private final UserService userServiceService;

    private final FirebaseCloudMessagingService firebaseCloudMessagingService;

    public ScheduleTask(TicketService ticketServiceService, UserService userServiceService, FirebaseCloudMessagingService firebaseCloudMessagingService) {
        this.ticketServiceService = ticketServiceService;
        this.userServiceService = userServiceService;
        this.firebaseCloudMessagingService = firebaseCloudMessagingService;
    }

    // 此时定时任务不会被执行
    //@Scheduled(fixedRate = 20000)
    public void printLog() throws IOException, FirebaseMessagingException {
        LOGGER.info("monitorTicketUpdates --run");
        // Invoke the findRecentlyUpdatedTickets method
        List<Ticket> recentlyUpdatedTickets = ticketServiceService.findRecentlyUpdatedTickets();
        LOGGER.info(recentlyUpdatedTickets.size() + "");
        // Process the recently updated tickets
        for (Ticket ticket : recentlyUpdatedTickets) {
            // Perform necessary operations with the ticket
            LOGGER.info(ticket.toString());
            List<String> targetDeviceTokens = userServiceService.getDeviceTokensForUser(ticket.getId() + "");
            PushNotificationPayload payload = firebaseCloudMessagingService.buildPayload(ticket);
            //yourService.FCMMessage();
            //  firebaseCloudMessagingService.sendPushNotification(payload, targetDeviceTokens);
        }
    }
}
