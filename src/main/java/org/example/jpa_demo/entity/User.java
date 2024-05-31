package org.example.jpa_demo.entity;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    private int id;
    private String username;
    private String password;
    private long userTypeId;
    private java.sql.Timestamp lastLoginTime;
    private String fcmToken;
    private String role;
    private String enabled;
    private String name;
    private String phone;


}
