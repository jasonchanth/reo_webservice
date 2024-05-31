package org.example.jpa_demo.mapper;

import org.example.jpa_demo.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setUserTypeId(rs.getLong("user_type_id"));
        user.setLastLoginTime(rs.getTimestamp("last_login_time"));
        user.setFcmToken(rs.getString("FCM_TOKEN"));
        user.setRole(rs.getString("role"));
        user.setEnabled(rs.getString("enabled"));
        user.setName(rs.getString("name"));
        user.setPhone(rs.getString("phone"));

        return user;
    }
}