package org.example.jpa_demo.repository;

import com.mysql.jdbc.Statement;
import org.example.jpa_demo.entity.Ticket;
import org.example.jpa_demo.mapper.TicketRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Repository
public class JdbcTicketRepository implements TicketRepository {
    private static final Log logger = LogFactory.getLog(JdbcTicketRepository.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTicketRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ticket> findRecentlyUpdatedTickets() {
        logger.info("findRecentlyUpdatedTickets start");
        LocalDateTime fiveMinutesAgo = LocalDateTime.now().minusMinutes(5);
        Timestamp timestamp = Timestamp.valueOf(fiveMinutesAgo);

        String sql = "SELECT t.*, ts.status FROM ticket t LEFT JOIN ticket_status ts ON t.ticket_status_id = ts.id WHERE t.updated_at <= ?";
        logger.info("timestamp" + timestamp);
        List<Ticket> tickets = jdbcTemplate.query(sql, new TicketRowMapper(), timestamp);
        logger.info(sql);

        return tickets;
    }

    @Override
    public List<Ticket> getTicketsByCreatedByUserId(String userId) {
        logger.info("getTicketsByUserId start");
        String sql = "SELECT t.*, ts.status FROM ticket t LEFT JOIN ticket_status ts ON t.ticket_status_id = ts.id WHERE t.createdby = ? ";
        logger.info(sql);
        return jdbcTemplate.query(sql, new TicketRowMapper(), userId);
    }
    public List<Ticket> getTicketsByAssignedToUserId(String userId) {
        logger.info("getTicketsByAssignedToUserId start");
        String sql = "SELECT t.*, ts.status FROM ticket t LEFT JOIN ticket_status ts ON t.ticket_status_id = ts.id WHERE t.assignedto = ? ";
        logger.info(sql);
        return jdbcTemplate.query(sql, new TicketRowMapper(), userId);
    }

    @Override
    public Ticket createTicket(String type, String subject, String details, int createdBy, int assignedTo) {
        logger.info("createTicket start");

        // Get the ticket type ID based on the provided type name
       /* String typeQuery = "SELECT id FROM ticket_type WHERE type = ?";
        Integer typeId = jdbcTemplate.queryForObject(typeQuery, Integer.class, type);

        if (typeId == null) {
            // Handle the case where the ticket type is not found
            // You can throw an exception or handle it based on your application's requirements
            throw new IllegalArgumentException("Invalid ticket type: " + type);
        }*/

        // Create a new ticket with the provided details
        Ticket newTicket = new Ticket();
        // newTicket.setType(typeId);  // Set the ticket type ID instead of the type name
        newTicket.setSubject(subject);
        //newTicket.setDetails(details);
        newTicket.setTicketStatusId(1);
        newTicket.setCreatedby(createdBy);
        newTicket.setAssignedto(assignedTo);
        // Set other ticket properties as needed

        // Prepare the SQL query for inserting a new ticket
        //String sql = "INSERT INTO ticket (type_id, subject, details, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        String sql = "INSERT INTO ticket (subject, ticket_status_id, createdby, assignedto,updated_at) VALUES (?, ?, ?, ?, ?)";

        // Create a GeneratedKeyHolder to retrieve the generated ID
        KeyHolder keyHolder = new GeneratedKeyHolder();
        // Get the current timestamp
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);

        logger.info("createTicket sql:" + sql);

        // Execute the SQL query to insert the new ticket
        // jdbcTemplate.update(sql, newTicket.getSubject(), newTicket.getTicketStatusId(), newTicket.getCreatedby(), newTicket.getAssignedto(), timestamp);

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, newTicket.getSubject());
            ps.setInt(2, newTicket.getTicketStatusId());
            ps.setInt(3, newTicket.getCreatedby());
            ps.setInt(4, newTicket.getAssignedto());
            ps.setTimestamp(5, timestamp);
            return ps;
        }, keyHolder);

        // Retrieve the generated ID and set it in the newTicket object
        if (keyHolder.getKey() != null) {
            int generatedId = keyHolder.getKey().intValue();
            newTicket.setId(generatedId);
        }
        logger.info("New ticket created. newTicket[" + newTicket + "]");
        return newTicket;
    }

    @Override
    public Ticket findTicketByTicketId(String ticketId) {
        logger.info("findTicketByTicketId start");
        String sql = "SELECT t.*, ts.status FROM ticket t LEFT JOIN ticket_status ts ON t.ticket_status_id = ts.id WHERE t.id = ?";
        logger.info(sql);
        return jdbcTemplate.queryForObject(sql, new TicketRowMapper(), ticketId);
    }


    @Override
    public Ticket closeTicket(int ticketID) {
        String sql = "UPDATE ticket SET ticket_status_id = 2 WHERE id = ?";
        jdbcTemplate.update(sql, ticketID);
        return findTicketByTicketId(ticketID+"");
    }

}