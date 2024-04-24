package org.example.jpa_demo.repository;

import org.example.jpa_demo.entity.User;
import org.example.jpa_demo.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Repository
public class JdbcUserRepository implements UserRepository {
    private static Log logger = LogFactory.getLog(JdbcUserRepository.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getUsersWithDetails() {
        String sql = "SELECT u.* FROM user u ";
        //  +  "JOIN departments d ON u.department_id = d.id";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
    }

    @Override
    public User findByName(String name) {
        String sql = "SELECT * FROM user WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), name);
    }

    @Override
    public String findRoleById(int id) {
        String sql = "SELECT user_type_id FROM user WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }


    @Override
    public List<String> getDeviceTokensForUserByTicketID(String ticketID) {
        // Implement the logic to retrieve the device tokens for the given user
        // Example: Query your database or external service to fetch the user's device tokens
        // Return a list of device tokens associated with the user
        String sql = "SELECT FCM_TOKEN FROM user WHERE ID IN (SELECT ticket.assignedto FROM ticket where id =?)";

        String FCM_TOKEN = jdbcTemplate.queryForObject(sql, String.class, ticketID);
        // System.out.println(sql);

        // Placeholder implementation
        List<String> deviceTokens = new ArrayList<>();
        //deviceTokens.add("fRAZ7_8nT2KBi1TbnTbwMs:APA91bF1uNUvOERoFZCLT5X27FMngaQlXhivow5CWE-0kH1wpsxr80pr3vK_3NBxfaV-KfbhHV4KRFOz_MpvgkRs42SMFtosEflY74CCQrA6VmcQdEv43gdxBeXpr6YINWfPxtvQ0V5i");
        deviceTokens.add(FCM_TOKEN);
        logger.info("deviceTokens" + deviceTokens);
        return deviceTokens;
    }

    @Override
    public User findByUsername(String name) {
        String sql = "SELECT * FROM user WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), name);
    }

    @Override
    public void updateUserFcmToken(User user) {
        String sql = "UPDATE user SET  fcm_token = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getFcmToken(), user.getId());
    }

}