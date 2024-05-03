package org.example.jpa_demo.mapper;

import org.example.jpa_demo.entity.PollingStationTask;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PollingStationTaskRowMapper implements RowMapper<PollingStationTask> {
    @Override
    public PollingStationTask mapRow(ResultSet rs, int rowNum) throws SQLException {
        PollingStationTask ps = new PollingStationTask();
        ps.setId(rs.getString("id"));
        ps.setTask1(rs.getTimestamp("task1"));
        ps.setTask2(rs.getTimestamp("task2"));
        ps.setTask3(rs.getTimestamp("task3"));
        ps.setTask4(rs.getTimestamp("task4"));
        ps.setTask5(rs.getTimestamp("task5"));
        ps.setTask6(rs.getTimestamp("task6"));
        ps.setTask7(rs.getInt("task7"));
        ps.setTotalActiveTable(rs.getInt("total_active_table"));

        return ps;
    }
}