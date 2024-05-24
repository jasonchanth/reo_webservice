package org.example.jpa_demo.service;
import org.example.jpa_demo.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(int id);

    User getUserByName(String name);

    String getUserRoleById(int id);

    List<String> getDeviceTokensForUser(String ticketID);

    boolean authenticateUser(String username, String password);

    void updateUserFcmToken(User user);

    void updateUserLastLoginTime(User user);
}