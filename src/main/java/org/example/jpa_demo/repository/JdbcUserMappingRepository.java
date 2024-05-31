package org.example.jpa_demo.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.jpa_demo.entity.Menu;
import org.example.jpa_demo.entity.User;
import org.example.jpa_demo.mapper.MenuRowMapper;
import org.example.jpa_demo.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcUserMappingRepository implements UserMappingRepository {
    private static Log logger = LogFactory.getLog(JdbcUserMappingRepository.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserMappingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public List<User> findAPROITUsers(int id) {
        String sql = "Select u.* from user u where id in (SELECT APROIT FROM user_mapping um WHERE SpecialT = ?)";
        logger.info("Executing SQL: " + sql + " with parameter: " + id);
        return jdbcTemplate.query(sql, new UserRowMapper(), id);
    }

    @Override
    public List<User> findSTUsers(int id) {
        String sql = "Select u.* from user u where id in (SELECT SpecialT FROM user_mapping um WHERE APROIT = ?)";
        logger.info("Executing SQL: " + sql + " with parameter: " + id);
        return jdbcTemplate.query(sql, new UserRowMapper(), id);
    }
}