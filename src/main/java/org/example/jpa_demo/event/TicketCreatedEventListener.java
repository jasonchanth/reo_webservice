package org.example.jpa_demo.event;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.example.jpa_demo.FirebaseCloudMessagingService;
import org.example.jpa_demo.entity.PushNotificationPayload;
import org.example.jpa_demo.entity.Ticket;
import org.example.jpa_demo.service.UserService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.List;

@Component
public class TicketCreatedEventListener {
    private static Log logger = LogFactory.getLog(TicketCreatedEventListener.class);
    private final UserService userServiceService;

    private final FirebaseCloudMessagingService firebaseCloudMessagingService;

    public TicketCreatedEventListener(UserService userServiceService, FirebaseCloudMessagingService firebaseCloudMessagingService) {
        this.userServiceService = userServiceService;
        this.firebaseCloudMessagingService = firebaseCloudMessagingService;
    }

    //@Async("asyncExecutor")
    @EventListener
    public void handleTicketCreatedEvent(TicketCreatedEvent event) throws IOException, FirebaseMessagingException {
        logger.info("handle Ticket Create Event start");
        Ticket ticket = event.getTicket();
        // Logic to send push notification using FCM
        logger.info(ticket.toString());
        List<String> targetDeviceTokens = userServiceService.getDeviceTokensForUser(ticket.getId() + "");
        PushNotificationPayload payload = firebaseCloudMessagingService.buildPayload(ticket);
        //yourService.FCMMessage();
        firebaseCloudMessagingService.sendPushNotification(payload, targetDeviceTokens);
    }
}
