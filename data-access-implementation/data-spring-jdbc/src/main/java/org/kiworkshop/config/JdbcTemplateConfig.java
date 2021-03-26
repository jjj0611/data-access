package org.kiworkshop.config;

import org.kiworkshop.dao.MemberDao;
import org.kiworkshop.jdbctemplate.MemberDaoJdbcTemplateImpl;
import org.kiworkshop.jdbctemplate.MemberDaoSimpleJdbcTemplateImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@Configuration
@Import(DataSourceConfig.class)
public class JdbcTemplateConfig {
    @Bean
    public MemberDao memberDaoJdbcTemplateImpl(DataSource dataSource) {
        return new MemberDaoJdbcTemplateImpl(dataSource);
    }

    @Bean
    public MemberDao memberDaoSimpleJdbcTemplateImpl(DataSource dataSource) {
        return new MemberDaoSimpleJdbcTemplateImpl(dataSource);
    }
}
