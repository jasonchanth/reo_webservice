package org.example.jpa_demo.repository;

import com.mysql.jdbc.Statement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.jpa_demo.entity.Threads;
import org.example.jpa_demo.mapper.ThreadsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcThreadsRepository implements ThreadsRepository {
    private static final Log logger = LogFactory.getLog(JdbcThreadsRepository.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcThreadsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Threads createThreads(int ticketid, int userid, String type, String message) {
        logger.info("create Threads start");

        // Get the ticket type ID based on the provided type name
       /* String typeQuery = "SELECT id FROM ticket_type WHERE type = ?";
        Integer typeId = jdbcTemplate.queryForObject(typeQuery, Integer.class, type);

        if (typeId == null) {
            // Handle the case where the ticket type is not found
            // You can throw an exception or handle it based on your application's requirements
            throw new IllegalArgumentException("Invalid ticket type: " + type);
        }*/

        // Create a new ticket with the provided details
        Threads newThreads = new Threads();
        // newTicket.setType(typeId);  // Set the ticket type ID instead of the type name
        newThreads.setTicketId(ticketid);
        newThreads.setUserId(userid);
        newThreads.setThreadType(type);
        newThreads.setMessage(message);
        // Set other ticket properties as needed

        // Prepare the SQL query for inserting a new ticket
        //String sql = "INSERT INTO ticket (type_id, subject, details, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        String sql = "INSERT INTO threads (ticket_id, user_id, thread_type, message, created_at) VALUES (?, ?, ?, ?, ?)";

        // Create a GeneratedKeyHolder to retrieve the generated ID
        KeyHolder keyHolder = new GeneratedKeyHolder();
        // Get the current timestamp
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);

        logger.info("create Threads sql:" + sql);

        // Execute the SQL query to insert the new ticket
        // jdbcTemplate.update(sql, newTicket.getSubject(), newTicket.getTicketStatusId(), newTicket.getCreatedby(), newTicket.getAssignedto(), timestamp);

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, newThreads.getTicketId());
            ps.setInt(2, newThreads.getUserId());
            ps.setString(3, newThreads.getThreadType());
            ps.setString(4, newThreads.getMessage());
            ps.setTimestamp(5, timestamp);
            return ps;
        }, keyHolder);

        // Retrieve the generated ID and set it in the newTicket object
        if (keyHolder.getKey() != null) {
            int generatedId = keyHolder.getKey().intValue();
            newThreads.setId(generatedId);
        }
        logger.info("New Threads created. newThreads[" + newThreads + "]");
        return newThreads;
    }

    @Override
    public List<Threads> getThreadsByTicketID(String ticketID) {
        logger.info("getThreadsByTicketID start");
        LocalDateTime fiveMinutesAgo = LocalDateTime.now().minusMinutes(5);
        Timestamp timestamp = Timestamp.valueOf(fiveMinutesAgo);

        // String sql = "SELECT t.*, ts.status FROM ticket t LEFT JOIN ticket_status ts ON t.ticket_status_id = ts.id WHERE t.updated_at <= ?";
        String sql = "SELECT t.*,(select attachment from thread_attachment ta where ta.thread_id = t.id ) as attachment FROM threads t where ticket_id=?";
        logger.info("timestamp: " + timestamp);
        List<Threads> threads = jdbcTemplate.query(sql, new ThreadsRowMapper(), ticketID);
        logger.info(sql);

        return threads;
    }

}