package org.example.jpa_demo;

import org.example.jpa_demo.entity.Ticket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class YourEntityRowMapper implements RowMapper<Ticket> {
    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ticket entity = new Ticket();
        // Map the result set columns to the corresponding fields of YourEntity
        // entity.setId(rs.getString("id"));
        //entity.setName(rs.getString("name"));
        //entity.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime().toString());
        // Map other columns as needed

        return entity;
    }
}