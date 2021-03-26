package org.kiworkshop.jdbctemplate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kiworkshop.config.DataSourceConfig;
import org.kiworkshop.config.JdbcTemplateConfig;
import org.kiworkshop.dao.MemberDao;
import org.kiworkshop.domain.Member;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberDaoSimpleJdbcTemplateImplTest {
    private MemberDao memberDao;

    @BeforeEach
    void setUp() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DataSourceConfig.class, JdbcTemplateConfig.class);
        memberDao = applicationContext.getBean("memberDaoSimpleJdbcTemplateImpl", MemberDao.class);
    }

    @AfterEach
    void tearDown() {
        memberDao.deleteAll();
    }

    @Test
    void createMember() {
        Member member = new Member(1L, "장재주", 1.0);
        memberDao.insert(member);
    }

    @Test
    void updateMember() {
        Member member = new Member(1L, "장재주", 1.0);
        memberDao.insert(member);

        member.changeName("김덕수");
        memberDao.update(member);

        Member memberDaoById = memberDao.findById(member.getId());
        assertThat(memberDaoById.getName()).isEqualTo("김덕수");
    }
}