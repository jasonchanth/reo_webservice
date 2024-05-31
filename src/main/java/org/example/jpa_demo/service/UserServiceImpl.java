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
    public String getUserRoleByUserName(String username) {
        return userRepository.findRoleByUserName(username);
    }


    @Override
    public List<String> getDeviceTokensForUser(String ticketID) {
        return userRepository.getDeviceTokensForUserByTicketID(ticketID);
    }

    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public void updateUserFcmToken(User user) {
        userRepository.updateUserFcmToken(user);
    }

    @Override
    public void updateUserLastLoginTime(User user) {
        userRepository.updateUserLastLoginTime(user);
    }
}
