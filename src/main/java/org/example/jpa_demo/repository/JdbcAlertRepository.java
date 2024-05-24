package org.example.jpa_demo.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.jpa_demo.entity.Alert;
import org.example.jpa_demo.entity.PollingStation;
import org.example.jpa_demo.entity.PollingStationTask;
import org.example.jpa_demo.mapper.AlertRowMapper;
import org.example.jpa_demo.mapper.PollingStationRowMapper;
import org.example.jpa_demo.mapper.PollingStationTaskRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcAlertRepository implements AlertRepository {
    private static final Log logger = LogFactory.getLog(JdbcAlertRepository.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAlertRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Alert> getAlertsByPSID(String PSID) {
        String sql = "SELECT a.* ,ps.ps_id,ps.answer FROM alert a left join alert_ps ps on a.id = ps.alert_id  where ps.ps_id = ? and a.active = 1";
        List<Alert> ps = jdbcTemplate.query(sql, new AlertRowMapper(), PSID);
        logger.info(sql);
        return ps;
    }

    @Override
    public String updateAlert(String alertid, String ps, String answer) {
        logger.info("updateAlert(alertid=" + alertid + ", ps=" + ps + ", answer=" + answer + ")");
        //update alert_ps set answer =? where ps_id =? and alert_id =?
        String sql = "update alert_ps set answer =? where ps_id =? and alert_id =?";
        int result = jdbcTemplate.update(sql, answer, ps, alertid);
        logger.info(sql);
        if (result > 0) {
            return "Success";
        }
        return null;
    }

}