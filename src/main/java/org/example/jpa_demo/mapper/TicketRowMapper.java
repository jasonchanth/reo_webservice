package org.example.jpa_demo.mapper;

import org.example.jpa_demo.entity.Ticket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketRowMapper implements RowMapper<Ticket> {
    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getInt("id"));
        ticket.setSubject(rs.getString("subject"));
        ticket.setTicketStatusId(rs.getInt("ticket_status_id"));
        ticket.setCreatedby(rs.getInt("createdby"));
        ticket.setAssignedto(rs.getInt("assignedto"));
        ticket.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

        // Set the status name from the joined table
        ticket.setStatus_name(rs.getString("status"));

        return ticket;
    }
}