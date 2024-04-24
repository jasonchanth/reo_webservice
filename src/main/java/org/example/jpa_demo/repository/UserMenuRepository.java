package org.example.jpa_demo.repository;

import org.example.jpa_demo.entity.Menu;

import java.util.List;

public interface UserMenuRepository {
    List<Menu> getUserMenuByRole(String role);
}
