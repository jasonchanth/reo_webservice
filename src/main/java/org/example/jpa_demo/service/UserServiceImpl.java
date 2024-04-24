package org.example.jpa_demo.service;

import org.example.jpa_demo.entity.User;
import org.example.jpa_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public String getUserRoleById(int id) {
        return userRepository.findRoleById(id);
    }


    @Override
    public List<String> getDeviceTokensForUser(String ticketID) {
        return userRepository.getDeviceTokensForUserByTicketID(ticketID);
    }

    public boolean authenticateUser(String username, String password) {
        // Implement your logic to authenticate the user
        // Check the username and password against the user table in the database
        // Return true if the credentials are correct, false otherwise
        // You can use any authentication mechanism (e.g., Spring Security, custom logic)
        // Here's a sample implementation using a mocked database:
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updateUserFcmToken(User user) {
        userRepository.updateUserFcmToken(user);
    }
}
