package org.example.jpa_demo;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.example.jpa_demo.entity.PushNotificationPayload;
import org.example.jpa_demo.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YourService {
    private final JdbcTemplate jdbcTemplate;

    private final FirebaseCloudMessagingService firebaseCloudMessagingService;

    @Autowired
    public YourService(DataSource dataSource, FirebaseCloudMessagingService firebaseCloudMessagingService) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.firebaseCloudMessagingService = firebaseCloudMessagingService;
    }

    public List<Ticket> findRecentlyUpdatedTickets() {
        LocalDateTime fiveMinutesAgo = LocalDateTime.now().minusMinutes(5);
        Timestamp timestamp = Timestamp.valueOf(fiveMinutesAgo);

        String sql = "SELECT * FROM ticket WHERE updated_at <= ?";
        List<Ticket> tickets = jdbcTemplate.query(sql, new YourEntityRowMapper(), timestamp);
        //System.out.println(sql);

        return tickets;
    }

    private PushNotificationPayload buildPayload(Ticket ticket) {
        PushNotificationPayload payload = new PushNotificationPayload();

        // Set the notification title and body
        payload.setTitle("你有一個新Ticket");
        payload.setBody("Ticket ID: " + ticket.getId() + " - Subject: 收唔到貨");

        // Set additional custom data, if needed
        Map<String, String> data = new HashMap<>();
        data.put("ticketId", ticket.getId() + "");
        payload.setData(data);

        return payload;
    }

    public void FCMMessage() throws IOException, FirebaseMessagingException {
        Ticket t = new Ticket();
        // t.setId("123");
        List<String> targetDeviceTokens = getDeviceTokensForUser(t.getId() + "");
        PushNotificationPayload payload = buildPayload(t);

        // Send the push notification request to Firebase Cloud Messaging
        firebaseCloudMessagingService.sendPushNotification(payload, targetDeviceTokens);
    }

    public List<String> getDeviceTokensForUser(String ticketID) {
        // Implement the logic to retrieve the device tokens for the given user
        // Example: Query your database or external service to fetch the user's device tokens
        // Return a list of device tokens associated with the user
        //  String sql = "SELECT FCM_TOKEN FROM users";
        //  List<String> deviceTokens = jdbcTemplate.query(sql, new YourEntityRowMapper());
        //System.out.println(sql);

        // Placeholder implementation
        List<String> deviceTokens = new ArrayList<>();
        deviceTokens.add("fRAZ7_8nT2KBi1TbnTbwMs:APA91bF1uNUvOERoFZCLT5X27FMngaQlXhivow5CWE-0kH1wpsxr80pr3vK_3NBxfaV-KfbhHV4KRFOz_MpvgkRs42SMFtosEflY74CCQrA6VmcQdEv43gdxBeXpr6YINWfPxtvQ0V5i");
        // deviceTokens.add("fRAZ7_8nT2KBi1TbnTbwMs:APA91bF1uNUvOERoFZCLT5X27FMngaQlXhivow5CWE-0kH1wpsxr80pr3vK_3NBxfaV-KfbhHV4KRFOz_MpvgkRs42SMFtosEflY74CCQrA6VmcQdEv43gdxBeXpr6YINWfPxtvQ0V5i");
        return deviceTokens;
    }
}