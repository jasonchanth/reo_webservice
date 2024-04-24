package org.example.jpa_demo.service;

import org.example.jpa_demo.entity.Menu;
import org.example.jpa_demo.entity.User;

import java.util.List;

public interface UserMenuService {
    List<Menu> getUserMenu(String role);

}