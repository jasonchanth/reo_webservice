package org.example.jpa_demo.repository;

import org.example.jpa_demo.entity.User;

import java.util.List;

public interface UserMappingRepository {
    List<User> findAPROITUsers(int id);

    List<User> findSTUsers(int id);
}
