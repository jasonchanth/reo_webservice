package org.example.jpa_demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Threads {

    private int id;
    private int ticketId;
    private int userId;
    private String threadType;
    private String message;
    private LocalDateTime createdAt;
    private String attachment;


}
