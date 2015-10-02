package com.github.uryyyyyyy.table3;

import java.sql.Array;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

@Component
public class Table3Dao {

    @Autowired
    private DataSource dataSource;

    public void insert() throws SQLException {
        List<Integer> keysSeed = Arrays.asList(1,2,4);
        Array arr = dataSource.getConnection().createArrayOf("integer", keysSeed.toArray());
        Table3Entity e = new Table3Entity("なまえ", arr);

        SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("table3");
        SqlParameterSource param = new BeanPropertySqlParameterSource(e);
        insert.execute(param);
    }

    public Set<Table3Entity> selectAll() {
        return new JdbcTemplate(dataSource).query("select * from table3", BeanPropertyRowMapper.newInstance(Table3Entity.class))
                .stream().collect(Collectors.toSet());
    }

    public Optional<Table3Entity> selectOne(int id) {
        try {
            Table3Entity e = new JdbcTemplate(dataSource).queryForObject("select * from table1 where id = ?",
                    BeanPropertyRowMapper.newInstance(Table3Entity.class), id);
            return Optional.of(e);
        }catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
