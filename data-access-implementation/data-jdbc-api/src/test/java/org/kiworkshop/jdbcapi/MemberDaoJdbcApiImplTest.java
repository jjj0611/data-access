package org.kiworkshop.jdbcapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kiworkshop.config.DataSourceConfig;
import org.kiworkshop.config.JdbcApiConfig;
import org.kiworkshop.dao.MemberDao;
import org.kiworkshop.domain.Member;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberDaoJdbcApiImplTest {
    private MemberDao memberDao;

    @BeforeEach
    void setUp() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DataSourceConfig.class, JdbcApiConfig.class);
        memberDao = applicationContext.getBean("memberDaoJdbcApiImpl", MemberDao.class);
    }

    @Test
    void createMember() {
        Member member = new Member(1L, "장재주", 1.0);
        memberDao.insert(member);

        List<Member> members = memberDao.findAll();
        assertThat(members).size().isEqualTo(1);
    }
}