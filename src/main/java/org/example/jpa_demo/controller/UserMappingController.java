package org.example.jpa_demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.jpa_demo.entity.Menu;
import org.example.jpa_demo.entity.User;
import org.example.jpa_demo.event.UserEventPublisher;
import org.example.jpa_demo.service.UserMappingService;
import org.example.jpa_demo.service.UserMenuService;
import org.example.jpa_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usermapping")
public class UserMappingController {

    private static final Log logger = LogFactory.getLog(UserMappingController.class);


    @Autowired
    private final UserService userService;
    @Autowired
    private final UserMappingService userMappingService;

    @Autowired
    public UserMappingController(UserService userService,UserMappingService userMappingService) {
        this.userService = userService;
        this.userMappingService = userMappingService;
    }


    @GetMapping("/{username}")
    public ResponseEntity<?> getUserMapping(@PathVariable String username) {
        User user = userService.getUserByName(username);
        List<User> users = userMappingService.getUserMappingList(user);
/*
        String role = userService.getUserRoleByUserName(username);

        List<User> users =new ArrayList<>();
        if (role != null) {

            users.add(userService.getUserByName(username));

        }*/

        logger.info(users);
        //  if (userMenu.isEmpty()) {
        //     return ResponseEntity.notFound().build();
        // } else {
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(users);
        }
        // }
    }


}