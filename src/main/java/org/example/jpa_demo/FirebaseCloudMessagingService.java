package org.example.jpa_demo;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.example.jpa_demo.entity.PushNotificationPayload;
import org.example.jpa_demo.entity.Threads;
import org.example.jpa_demo.entity.Ticket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.messaging.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@Service
public class FirebaseCloudMessagingService {
    // @Value("${fcm.server-key}")
    private static Log logger = LogFactory.getLog(FirebaseCloudMessagingService.class);
    private String fcmServerKey;

    public void sendPushNotification(PushNotificationPayload payload, List<String> targetDeviceTokens) throws IOException, FirebaseMessagingException {
        // Load the Firebase service account credentials
        FileInputStream serviceAccountFile = new FileInputStream("D:\\firebase\\helpdeskdemo-11bef-firebase-adminsdk-ypu95-e77fad3d70.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountFile);

        // Build the FirebaseMessaging instance
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);
        }
        FirebaseMessaging messaging = FirebaseMessaging.getInstance();
        Notification n = Notification.builder().setTitle(payload.getTitle()).setBody(payload.getBody()).setImage(null).build();


        // Build the message with the payload and target device tokens
        MulticastMessage.Builder messageBuilder = MulticastMessage.builder().setNotification(n).setFcmOptions(FcmOptions.withAnalyticsLabel("helpdeskTicket"))
                .putData("title", payload.getTitle())
                .putData("body", payload.getBody())
                .addAllTokens(targetDeviceTokens);
        logger.info("title:" + payload.getTitle());
        logger.info("body:" + payload.getBody());
        logger.info("targetDeviceTokens:" + targetDeviceTokens);


        // Set additional custom data, if needed
        if (payload.getData() != null) {
            for (String key : payload.getData().keySet()) {
                messageBuilder.putData(key, payload.getData().get(key));
                logger.info("Key:" + payload.getData().get(key));
                logger.info("Data:" + payload.getData());
            }
        }
        logger.info("messageBuilder.toString():" + messageBuilder.toString());
        // Send the message using the FirebaseMessaging instance
        BatchResponse response = messaging.sendMulticast(messageBuilder.build());

        // Handle the response as needed
        // You can access the response details using response.getResponses()
    }

    public PushNotificationPayload buildPayload(Ticket ticket) {
        PushNotificationPayload payload = new PushNotificationPayload();

        // Set the notification title and body
        payload.setTitle("你有一個新Ticket");
        payload.setBody("Ticket ID: " + ticket.getId() + " - Subject:" + ticket.getSubject());

        // Set additional custom data, if needed
        Map<String, String> data = new HashMap<>();
        data.put("ticketId", String.valueOf(ticket.getId()));
        payload.setData(data);

        return payload;
    }

    public PushNotificationPayload buildPayloadForThreads(Threads threads) {
        PushNotificationPayload payload = new PushNotificationPayload();

        // Set the notification title and body
        payload.setTitle("你有一個新Update");
        payload.setBody("Ticket ID: " + threads.getId() + " - message:" + threads.getMessage());

        // Set additional custom data, if needed
        Map<String, String> data = new HashMap<>();
        data.put("ticketId", String.valueOf(threads.getId()));
        payload.setData(data);

        return payload;
    }
    /*
    public List<String> getDeviceTokensForUser(String userId) {
        // Implement the logic to retrieve the device tokens for the given user
        // Example: Query your database or external service to fetch the user's device tokens
        // Return a list of device tokens associated with the user

        // Placeholder implementation
        List<String> deviceTokens = new ArrayList<>();
        deviceTokens.add("fRAZ7_8nT2KBi1TbnTbwMs:APA91bF1uNUvOERoFZCLT5X27FMngaQlXhivow5CWE-0kH1wpsxr80pr3vK_3NBxfaV-KfbhHV4KRFOz_MpvgkRs42SMFtosEflY74CCQrA6VmcQdEv43gdxBeXpr6YINWfPxtvQ0V5i");
       // deviceTokens.add("fRAZ7_8nT2KBi1TbnTbwMs:APA91bF1uNUvOERoFZCLT5X27FMngaQlXhivow5CWE-0kH1wpsxr80pr3vK_3NBxfaV-KfbhHV4KRFOz_MpvgkRs42SMFtosEflY74CCQrA6VmcQdEv43gdxBeXpr6YINWfPxtvQ0V5i");
        return deviceTokens;
    }*/
}
