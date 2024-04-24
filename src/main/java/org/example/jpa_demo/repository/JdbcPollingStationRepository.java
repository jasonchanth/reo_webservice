package org.example.jpa_demo.repository;

import com.mysql.jdbc.Statement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.jpa_demo.entity.PollingStation;
import org.example.jpa_demo.entity.Threads;
import org.example.jpa_demo.mapper.PollingStationRowMapper;
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
public class JdbcPollingStationRepository implements PollingStationRepository {
    private static final Log logger = LogFactory.getLog(JdbcPollingStationRepository.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPollingStationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PollingStation> getPollingStationsByID(String id) {
        String sql = "SELECT ps.* FROM pollingstation ps where id in (select pollingstationid from pollingstation_user where userid = ?)";
        List<PollingStation> ps = jdbcTemplate.query(sql, new PollingStationRowMapper(), id);
        logger.info(sql);
        return ps;
    }
}