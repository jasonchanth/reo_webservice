package org.example.jpa_demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.jpa_demo.entity.Menu;
import org.example.jpa_demo.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usermapping")
public class UserMappingController {

    private static final Log logger = LogFactory.getLog(UserMappingController.class);


    @Autowired
    private UserMenuService userMenuService;


    @GetMapping("/{role}")
    public ResponseEntity<?> getUserMapping(@PathVariable String role) {

        List<Menu> userMenu = userMenuService.getUserMenu(role);
        logger.info(role);
        //  if (userMenu.isEmpty()) {
        //     return ResponseEntity.notFound().build();
        // } else {
        return ResponseEntity.ok(userMenu);
        // }
    }

}