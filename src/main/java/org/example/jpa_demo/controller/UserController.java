package org.example.jpa_demo.controller;

import org.example.jpa_demo.entity.User;
import org.example.jpa_demo.event.UserEventPublisher;
import org.example.jpa_demo.event.UserLogInEvent;
import org.example.jpa_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@RestController
public class UserController {

    private static final Log logger = LogFactory.getLog(UserController.class);

    private final UserService userService;
    private final UserEventPublisher userEventPublisher;

    @Autowired
    public UserController(UserService userService, UserEventPublisher userEventPublisher) {
        this.userService = userService;
        this.userEventPublisher = userEventPublisher;
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("fcmToken") String fcmToken) {
        logger.info("login start");
        logger.info("username:" + username);
        logger.info("password:" + password);
        logger.info("fcmToken:" + fcmToken);

        // Check the credentials against the user table in the database
        // Replace this code with your own logic to authenticate the user
        boolean isAuthenticated = false;

        User user = userService.getUserByName(username);
        if (user != null && user.getPassword().equals(password)) {
            isAuthenticated = true;
        }

        if (isAuthenticated) {
            // Update the FCM_TOKEN column in the user table
            UserLogInEvent loginEvent = new UserLogInEvent(user);
            loginEvent.getUser().setFcmToken(fcmToken);
            //loginEvent.getUser().setFcmToken("111111222223333");
            userEventPublisher.publishUserLoginEvent(loginEvent.getUser());

            // Return a JSON response with success set to true
            return ResponseEntity.ok().body("{\"success\": true,\"userID\":" + user.getId() + "}");
        } else {
            // Return a JSON response with success set to false
            return ResponseEntity.ok().body("{\"success\": false}");
        }


    }

}