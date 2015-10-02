package com.github.uryyyyyyy.table2;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

@Component
public class Table2Dao {

    @Autowired
    private DataSource dataSource;

    public void insert() {
        ZonedDateTime z = ZonedDateTime.now();
        Timestamp t1 = Timestamp.valueOf(z.toLocalDateTime());
        Time t2 = Time.valueOf(z.toLocalTime());
        Table2Entity e = new Table2Entity(t1, t1, t2);

        SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("table2");

        SqlParameterSource param = new BeanPropertySqlParameterSource(e);
        insert.execute(param);
    }

    public Set<Table2Entity> selectAll() {
        return new JdbcTemplate(dataSource).query("select * from table2", BeanPropertyRowMapper.newInstance(Table2Entity.class))
                .stream().collect(Collectors.toSet());
    }
}
