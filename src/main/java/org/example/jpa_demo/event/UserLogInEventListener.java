package org.example.jpa_demo.event;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.jpa_demo.FirebaseCloudMessagingService;
import org.example.jpa_demo.entity.PushNotificationPayload;
import org.example.jpa_demo.entity.User;
import org.example.jpa_demo.service.UserService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class UserLogInEventListener {
    private static Log logger = LogFactory.getLog(UserLogInEventListener.class);
    private final UserService userService;

    private final FirebaseCloudMessagingService firebaseCloudMessagingService;

    public UserLogInEventListener(UserService userService, FirebaseCloudMessagingService firebaseCloudMessagingService) {
        this.userService = userService;
        this.firebaseCloudMessagingService = firebaseCloudMessagingService;
    }

    //@Async("asyncExecutor")
    @EventListener
    public void handleUserLogInEvent(UserLogInEvent event) throws IOException, FirebaseMessagingException {
        logger.info("handle User Login Event start");
        User user = event.getUser();
        logger.info("handleUserLogInEvent:" + user.getFcmToken());
        userService.updateUserFcmToken(user);
        userService.updateUserLastLoginTime(user);
    }
}
