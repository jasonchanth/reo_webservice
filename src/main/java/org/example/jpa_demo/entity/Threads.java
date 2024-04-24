package org.example.jpa_demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Threads {

    private int id;
    private int ticketId;
    private int userId;
    private String threadType;
    private String message;
    private java.sql.Timestamp createdAt;


}
