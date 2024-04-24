package org.example.jpa_demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ticket {

    private int id;
    private String subject;
    private int ticketStatusId;
    private int createdby;
    private int assignedto;
    private java.sql.Timestamp updatedAt;

    private String status_name;

    @Override
    public String toString() {
        return String.format(
                "Ticket[id=%s, subject='%s', ticketStatuId='%s',createdby='%s',assignedto='%s',updatedAt='%s']",
                id, subject, ticketStatusId, createdby, assignedto, updatedAt);
    }

/*
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getSubject() {
    return subject;
  }
  public void setSubject(String subject) {
    this.subject = subject;
  }


  public long getTicketStatusId() {
    return ticketStatusId;
  }

  public void setTicketStatusId(long ticketStatusId) {
    this.ticketStatusId = ticketStatusId;
  }


  public long getCreatedby() {
    return createdby;
  }

  public void setCreatedby(long createdby) {
    this.createdby = createdby;
  }


  public long getAssignedto() {
    return assignedto;
  }

  public void setAssignedto(long assignedto) {
    this.assignedto = assignedto;
  }


  public java.sql.Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(java.sql.Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void setStatusName(String statusName) {
    this.status_name = statusName;
  }
  public String getStatusName() {
    return status_name;
  }*/

}
