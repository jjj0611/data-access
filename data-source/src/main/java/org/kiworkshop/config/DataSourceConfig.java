package org.kiworkshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    @Primary
    public DataSource simpleH2DriverDataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUrl("jdbc:h2:mem/testdb");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        return dataSource;
    }

//    @Bean
//    public DataSource simpleMySQLDataSource() {
//        SimpleDriverDataSource dataSource = new SimpleDriverDataSource ();
//
//        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
//        dataSource.setUrl("jdbc:mysql://localhost:53306/springbook?characterEncoding=UTF-8");
//        dataSource.setUsername("spring");
//        dataSource.setPassword("book");
//
//        return dataSource;
//    }

//    @Bean
//    public DataSource singleConnectionDataSource() {
//        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:53306/springbook?characterEncoding=UTF-8");
//        dataSource.setUsername("spring");
//        dataSource.setPassword("book");
//
//        return dataSource;
//    }

//    @Bean
//    public DataSource hikariPoolDataSource() {
//        return null;
//    }
}
