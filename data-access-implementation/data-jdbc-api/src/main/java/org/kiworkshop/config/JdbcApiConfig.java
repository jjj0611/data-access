package org.kiworkshop.config;

import org.kiworkshop.dao.MemberDao;
import org.kiworkshop.jdbcapi.MemberDaoJdbcApiImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@Configuration
@Import(DataSourceConfig.class)
public class JdbcApiConfig {
    @Bean
    public MemberDao memberDaoJdbcApiImpl(DataSource dataSource) {
        return new MemberDaoJdbcApiImpl(dataSource);
    }
}
