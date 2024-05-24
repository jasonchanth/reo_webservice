package org.example.jpa_demo.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Alert {

  private long id;
  private String question;
  private String type;
  private long active;
  private String ps;
  private String answer;
}
