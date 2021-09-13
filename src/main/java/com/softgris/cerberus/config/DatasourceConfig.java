package com.softgris.cerberus.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.ConnectionHandle;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleConnectionHandle;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
public class DatasourceConfig {

//    @Bean
//    @ConfigurationProperties(prefix="spring.datasource")
//    public DataSource getDataSource() {
//        return new DriverManagerDataSource();
//    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public HikariDataSource hikariDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
