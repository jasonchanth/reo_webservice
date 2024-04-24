package org.example.jpa_demo.mapper;

import org.example.jpa_demo.entity.PollingStation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PollingStationRowMapper implements RowMapper<PollingStation> {
    @Override
    public PollingStation mapRow(ResultSet rs, int rowNum) throws SQLException {
        PollingStation ps = new PollingStation();
        ps.setId(rs.getString("id"));
        ps.setOwner(rs.getString("owner"));
        ps.setAddress(rs.getString("address"));
        ps.setPhone(rs.getString("phone"));

        return ps;
    }
}