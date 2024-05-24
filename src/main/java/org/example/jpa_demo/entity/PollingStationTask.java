package org.example.jpa_demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PollingStationTask {

  private String id;
  private String pollingstationid;
  private LocalDateTime task1;
  private LocalDateTime task2;
  private LocalDateTime task3;
  private LocalDateTime task4;
  private LocalDateTime task5;
  private LocalDateTime task6;
  private int task7;
  private int totalActiveTable;

}
