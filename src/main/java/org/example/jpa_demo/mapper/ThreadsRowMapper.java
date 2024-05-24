package org.example.jpa_demo.mapper;

import org.example.jpa_demo.entity.Threads;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ThreadsRowMapper implements RowMapper<Threads> {
    @Override
    public Threads mapRow(ResultSet rs, int rowNum) throws SQLException {
        Threads threads = new Threads();
        threads.setId(rs.getInt("id"));
        threads.setTicketId(rs.getInt("ticket_id"));
        threads.setUserId(rs.getInt("user_id"));
        threads.setThreadType(rs.getString("thread_type"));
        threads.setMessage(rs.getString("message"));
        threads.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        threads.setAttachment(rs.getString("attachment"));


        return threads;
    }
}