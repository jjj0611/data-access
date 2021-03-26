package org.kiworkshop.config;

import org.kiworkshop.dao.MemberDao;
import org.kiworkshop.jdbctemplate.MemberDaoJdbcTemplateImpl;
import org.kiworkshop.jdbctemplate.MemberDaoSimpleJdbcTemplateImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@Import(DataSourceConfig.class)
public class JdbcTemplateConfig {
    @Bean
    public MemberDao memberDaoJdbcTemplateImpl(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return new MemberDaoJdbcTemplateImpl(jdbcTemplate);
    }

    @Bean
    public MemberDao memberDaoSimpleJdbcTemplateImpl(DataSource dataSource) {
        SimpleJdbcTemplate simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
        return new MemberDaoSimpleJdbcTemplateImpl(simpleJdbcTemplate);
    }
}
