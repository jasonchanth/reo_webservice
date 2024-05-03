package org.example.jpa_demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PollingStationTask {

  private String id;
  private String pollingstationid;
  private java.sql.Timestamp task1;
  private java.sql.Timestamp task2;
  private java.sql.Timestamp task3;
  private java.sql.Timestamp task4;
  private java.sql.Timestamp task5;
  private java.sql.Timestamp task6;
  private int task7;
  private int totalActiveTable;

}
