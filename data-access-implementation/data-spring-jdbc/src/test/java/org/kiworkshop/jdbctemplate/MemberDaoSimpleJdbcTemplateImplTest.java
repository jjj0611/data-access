package org.kiworkshop.jdbctemplate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kiworkshop.config.DataSourceConfig;
import org.kiworkshop.config.JdbcTemplateConfig;
import org.kiworkshop.dao.MemberDao;
import org.kiworkshop.domain.Member;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class MemberDaoSimpleJdbcTemplateImplTest {
    private MemberDao memberDao;

    @BeforeEach
    void setUp() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DataSourceConfig.class, JdbcTemplateConfig.class);
        memberDao = applicationContext.getBean("memberDaoSimpleJdbcTemplateImpl", MemberDao.class);
    }

    @Test
    void createMember() {
        Member member = new Member(1L, "장재주", 1.0);
        memberDao.insert(member);
    }
}