package org.kiworkshop.config;

import org.kiworkshop.dao.MemberDao;
import org.kiworkshop.jdbctemplate.MemberDaoJdbcTemplateImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@Import(DataSourceConfig.class)
public class JdbcTemplateConfig {
    @Bean
    public MemberDao memberDao(JdbcTemplate jdbcTemplate) {
        return new MemberDaoJdbcTemplateImpl(jdbcTemplate);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
