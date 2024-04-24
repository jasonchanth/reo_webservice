package org.example.jpa_demo.service;

import org.example.jpa_demo.entity.Menu;
import org.example.jpa_demo.entity.User;
import org.example.jpa_demo.repository.UserMenuRepository;
import org.example.jpa_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMenuServiceImpl implements UserMenuService {

    @Autowired
    private UserMenuRepository userMenuRepository;

    @Override
    public List<Menu> getUserMenu(String role) {
        return userMenuRepository.getUserMenuByRole(role);
    }
}
