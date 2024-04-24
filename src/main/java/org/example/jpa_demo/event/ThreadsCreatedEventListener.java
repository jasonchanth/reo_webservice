package org.example.jpa_demo.event;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.jpa_demo.FirebaseCloudMessagingService;
import org.example.jpa_demo.entity.PushNotificationPayload;
import org.example.jpa_demo.entity.Threads;
import org.example.jpa_demo.entity.Ticket;
import org.example.jpa_demo.service.TicketService;
import org.example.jpa_demo.service.UserService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ThreadsCreatedEventListener {
    private static Log logger = LogFactory.getLog(ThreadsCreatedEventListener.class);
    private final UserService userService;
    private final TicketService ticketService;

    private final FirebaseCloudMessagingService firebaseCloudMessagingService;

    public ThreadsCreatedEventListener(UserService userService, TicketService ticketService, FirebaseCloudMessagingService firebaseCloudMessagingService) {
        this.userService = userService;
        this.ticketService = ticketService;
        this.firebaseCloudMessagingService = firebaseCloudMessagingService;
    }

    //@Async("asyncExecutor")
    @EventListener
    public void handleTicketCreatedEvent(ThreadsCreatedEvent event) throws IOException, FirebaseMessagingException {
        logger.info("handle Threads Create Event start");
        Threads threads = event.getThreads();
        // Logic to send push notification using FCM
        logger.info(threads.toString());
        List<String> targetDeviceTokens = userService.getDeviceTokensForUser(threads.getId() + "");
        //Ticket ticket = ticketService.getTicketByThreads(threads);
        PushNotificationPayload payload = firebaseCloudMessagingService.buildPayloadForThreads(threads);
        //yourService.FCMMessage();
        firebaseCloudMessagingService.sendPushNotification(payload, targetDeviceTokens);
    }
}
