package org.example.jpa_demo.mapper;

import org.example.jpa_demo.entity.PollingStationSchedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PollingStationScheduleRowMapper implements RowMapper<PollingStationSchedule> {
    @Override
    public PollingStationSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        PollingStationSchedule ps = new PollingStationSchedule();
        ps.setId(rs.getString("id"));
        ps.setPollingstationid(rs.getString("pollingstationid"));
        ps.setTask1(rs.getInt("task1"));
        ps.setTask2(rs.getInt("task2"));
        ps.setTask3(rs.getInt("task3"));
        ps.setTask4(rs.getInt("task4"));
        ps.setTask5(rs.getInt("task5"));
        ps.setTask6(rs.getInt("task6"));
        ps.setTask7(rs.getInt("task7"));

        return ps;
    }
}