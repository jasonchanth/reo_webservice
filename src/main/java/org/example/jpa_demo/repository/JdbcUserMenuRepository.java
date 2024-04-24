package org.example.jpa_demo.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.jpa_demo.entity.Menu;
import org.example.jpa_demo.entity.User;
import org.example.jpa_demo.mapper.MenuRowMapper;
import org.example.jpa_demo.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcUserMenuRepository implements UserMenuRepository {
    private static Log logger = LogFactory.getLog(JdbcUserMenuRepository.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserMenuRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Menu> getUserMenuByRole(String role) {
        String sql = "SELECT m.* FROM menu m WHERE role like ?";
        String likeParam = "%" + role + "%";
        return jdbcTemplate.query(sql, new MenuRowMapper(), likeParam);
    }


}