package com.github.uryyyyyyy.table3;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


@Component
@Configuration
@ComponentScan
@EnableTransactionManagement
public class Main {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        context.getBean(Main.class).run();
    }

    @Autowired
    private Table3Dao table1Dao;

    @Transactional
    public void run() throws SQLException {
        table1Dao.insert();
        Set<Table3Entity> es = table1Dao.selectAll();
        es.stream().forEach(v -> {
            try {
                Integer[] keys_ = (Integer[]) v.getKeys().getArray();
                System.out.println(v.getName() + ", " + Arrays.toString(keys_));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Bean
    protected DataSource createDataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.postgresql.Driver.class);
        dataSource.setUrl("jdbc:postgresql://localhost/my_database");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }

    @Bean
    @Autowired
    protected PlatformTransactionManager createTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}