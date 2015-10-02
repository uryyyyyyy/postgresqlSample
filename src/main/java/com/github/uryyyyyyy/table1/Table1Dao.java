package com.github.uryyyyyyy.table1;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class Table1Dao {

    @Autowired
    private DataSource dataSource;

    public Table1Entity insert() {
        Table1Entity e = new Table1Entity(null, "なまえ");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        new NamedParameterJdbcTemplate(jdbcTemplate).update(
                "INSERT INTO table1 (name) VALUES (:name)",
                new BeanPropertySqlParameterSource(e), keyHolder);
        e.setId((int)keyHolder.getKeys().get("id"));
        return e;
    }
}
