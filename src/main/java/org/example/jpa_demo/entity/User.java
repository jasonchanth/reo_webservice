package org.example.jpa_demo.entity;


public class User {

    private long id;
    private String username;
    private String password;
    private long userTypeId;
    private java.sql.Timestamp lastLoginTime;
    private String fcmToken;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public long getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(long userTypeId) {
        this.userTypeId = userTypeId;
    }


    public java.sql.Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(java.sql.Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }


    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

}
