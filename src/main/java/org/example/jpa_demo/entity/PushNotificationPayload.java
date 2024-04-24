package org.example.jpa_demo.entity;

import java.util.Map;

public class PushNotificationPayload {
    private String title;
    private String body;
    private Map<String, String> data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    // Constructors, getters, and setters

    // Additional methods as needed
}