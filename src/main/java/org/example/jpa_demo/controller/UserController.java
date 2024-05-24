package org.example.jpa_demo.controller;

import org.example.jpa_demo.JwtUtil;
import org.example.jpa_demo.entity.User;
import org.example.jpa_demo.event.UserEventPublisher;
import org.example.jpa_demo.event.UserLogInEvent;
import org.example.jpa_demo.service.UserDetailsServiceImpl;
import org.example.jpa_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.nio.charset.StandardCharsets;


@RestController
public class UserController {

    @Autowired
    private JwtUtil jwtTokenUtil;


    @Autowired
    private AuthenticationManager authenticationManager;

    //@Autowired
    //private JwtUtil jwtUtil;

    private static final Log logger = LogFactory.getLog(UserController.class);

    private final UserService userService;
    private final UserEventPublisher userEventPublisher;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

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
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
       // String encodepassword =encoder.encode(password);

        // Check the credentials against the user table in the database
        // Replace this code with your own logic to authenticate the user
        boolean isAuthenticated = false;

        //UsernamePasswordAuthenticationToken authenticationToken =
         //       new UsernamePasswordAuthenticationToken(username,password);
        //authenticationManager.authenticate(authenticationToken);



        User user = userService.getUserByName(username);
        logger.info("user.getPassword():" + user.getPassword());
       // logger.info("encodepassword:" + encodepassword);

        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
        //final String token = jwtUtil.generateToken(userDetails);

       /* String token = JWT.create()
//                .setExpiresAt(new Date(System.currentTimeMillis() + (1000 * 30)))
                .setPayload("username", username)
                .setKey("key".getBytes(StandardCharsets.UTF_8))
                .sign();*/

        String token = jwtTokenUtil.generateToken(userDetails);

        logger.info("token:" + token);


        if (encoder.matches(password, user.getPassword())) {
            isAuthenticated = true;
        }
        /*if (user != null && user.getPassword().equals(encodepassword)) {
            isAuthenticated = true;
        }*/
        if (isAuthenticated) {
            // Update the FCM_TOKEN column in the user table
            UserLogInEvent loginEvent = new UserLogInEvent(user);
            loginEvent.getUser().setFcmToken(fcmToken);
            //loginEvent.getUser().setFcmToken("111111222223333");
            userEventPublisher.publishUserLoginEvent(loginEvent.getUser());

            // Return a JSON response with success set to true
            return ResponseEntity.ok().body("{\"success\": true,\"userID\":" + user.getId() + ",\"userRole\":\"" + user.getRole() + "\",\"token\":\"" + token +  "\"}");
        } else {
            // Return a JSON response with success set to false
            return ResponseEntity.ok().body("{\"success\": false}");
        }


    }

}