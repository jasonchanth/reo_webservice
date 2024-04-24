package org.example.jpa_demo.repository;

import org.example.jpa_demo.entity.Threads;
import org.example.jpa_demo.entity.Ticket;
import org.example.jpa_demo.entity.User;

import java.util.List;

public interface UserRepository {
    List<User> getUsersWithDetails();

    User findById(int id);

    User findByName(String name);

    String findRoleById(int id);

    List<String> getDeviceTokensForUserByTicketID(String ticketID);

    User findByUsername(String name);

    void updateUserFcmToken(User user);
}
