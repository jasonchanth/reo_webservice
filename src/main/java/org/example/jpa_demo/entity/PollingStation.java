package org.example.jpa_demo.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PollingStation {

  private String id;
  private String owner;
  private String address;
  private String phone;

}
