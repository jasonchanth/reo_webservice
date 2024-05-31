package org.example.jpa_demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PollingStationSchedule {

  private String id;
  private String pollingstationid;
  private int task1;
  private int task2;
  private int task3;
  private int task4;
  private int task5;
  private int task6;
  private int task7;
  private LocalDateTime last_update;


}
