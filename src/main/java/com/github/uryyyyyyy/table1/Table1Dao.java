package com.github.uryyyyyyy.table1;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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

    public Table1Entity insertWithORM() {
        Table1Entity e = new Table1Entity(null, "なまえ");
        SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("table1").usingGeneratedKeyColumns("id");

        SqlParameterSource param = new BeanPropertySqlParameterSource(e);
        if(e.getId() == null) {
            Number key = insert.executeAndReturnKey(param);
            e.setId(key.intValue());
            return e;
        }
        throw new RuntimeException();
    }

    public Table1Entity update() {
        Table1Entity e = new Table1Entity(null, "なまえ");
        SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("table1").usingGeneratedKeyColumns("id");

        SqlParameterSource param = new BeanPropertySqlParameterSource(e);
        if(e.getId() == null) {
            Number key = insert.executeAndReturnKey(param);
            e.setId(key.intValue());
            return e;
        }
        throw new RuntimeException();
    }

    public Set<Table1Entity> selectAll() {
        return new JdbcTemplate(dataSource).query("select * from table1", BeanPropertyRowMapper.newInstance(Table1Entity.class))
                .stream().collect(Collectors.toSet());
    }

    public Optional<Table1Entity> selectOne(int id) {
        try {
            Table1Entity e = new JdbcTemplate(dataSource).queryForObject("select * from table1 where id = ?",
                    BeanPropertyRowMapper.newInstance(Table1Entity.class), id);
            return Optional.of(e);
        }catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
