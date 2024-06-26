package org.example.jpa_demo.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.jpa_demo.entity.PollingStation;
import org.example.jpa_demo.entity.PollingStationTask;
import org.example.jpa_demo.entity.PollingStationSchedule;
import org.example.jpa_demo.mapper.PollingStationRowMapper;
import org.example.jpa_demo.mapper.PollingStationTaskRowMapper;
import org.example.jpa_demo.mapper.PollingStationScheduleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcPollingStationRepository implements PollingStationRepository {
    private static final Log logger = LogFactory.getLog(JdbcPollingStationRepository.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPollingStationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PollingStation> getPollingStationsByUserID(String userID) {
        String sql = "SELECT ps.* FROM pollingstation ps where id in (select pollingstationid from pollingstation_user where userid = ?)";
        List<PollingStation> ps = jdbcTemplate.query(sql, new PollingStationRowMapper(), userID);
        logger.info("getPollingStationsByUserID" + sql + " UserID: "+userID);
        return ps;
    }
    @Override
    public  List<PollingStationTask> getTaskByPollingStationID(String pollingstationid) {
        String sql = "SELECT task.* FROM pollingstation_task task where pollingstationid = ?";
        List<PollingStationTask> ps = jdbcTemplate.query(sql, new PollingStationTaskRowMapper(), pollingstationid);
        logger.info(ps);
        return ps;
    }

    @Override
    public List<PollingStationSchedule> getScheduleByPollingStationID(String pollingstationid) {
        String sql = "SELECT schedule.* FROM pollingstation_schedule schedule where pollingstationid = ?";
        List<PollingStationSchedule> ps = jdbcTemplate.query(sql, new PollingStationScheduleRowMapper(), pollingstationid);
        logger.info(ps);
        return ps;
    }
}