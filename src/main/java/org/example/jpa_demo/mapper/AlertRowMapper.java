package org.example.jpa_demo.mapper;

import org.example.jpa_demo.entity.Alert;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlertRowMapper implements RowMapper<Alert> {
    @Override
    public Alert mapRow(ResultSet rs, int rowNum) throws SQLException {
        Alert alert = new Alert();
        alert.setId(rs.getLong("id"));
        alert.setQuestion(rs.getString("question"));
        alert.setType(rs.getString("type"));
        alert.setActive(rs.getInt("active"));
        alert.setPs(rs.getString("ps_id"));
        alert.setAnswer(rs.getString("answer"));


        return alert;
    }
}