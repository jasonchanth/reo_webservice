package org.example.jpa_demo.mapper;

import org.example.jpa_demo.entity.Menu;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuRowMapper implements RowMapper<Menu> {
    @Override
    public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
        Menu menu = new Menu();
        menu.setId(rs.getLong("id"));
        menu.setMenuName(rs.getString("menu_name"));
        menu.setMenuImg(rs.getString("menu_img"));
        menu.setMenuPath(rs.getString("menu_path"));
        menu.setRole(rs.getString("role"));

        return menu;
    }
}