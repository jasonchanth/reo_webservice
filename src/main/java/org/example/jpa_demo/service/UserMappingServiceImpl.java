package org.example.jpa_demo.service;

import org.example.jpa_demo.entity.Menu;
import org.example.jpa_demo.entity.User;
import org.example.jpa_demo.repository.UserMappingRepository;
import org.example.jpa_demo.repository.UserMenuRepository;
import org.example.jpa_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMappingServiceImpl implements UserMappingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMappingRepository userMappingRepository;


    @Override
    public List<User> getUserMappingList(User user) {
        List<User> userList = new ArrayList<User>();
        User originalUser =userRepository.findByUsername(user.getUsername());

        if (originalUser != null) {
            if(!originalUser.getRole().equals("APROIT")) {
                userList = userMappingRepository.findAPROITUsers(originalUser.getId());

            }else{
                userList = userMappingRepository.findSTUsers(originalUser.getId());
            }

        }


        return userList;
    }
}
