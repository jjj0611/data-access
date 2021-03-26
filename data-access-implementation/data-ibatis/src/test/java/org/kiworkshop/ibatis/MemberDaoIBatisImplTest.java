package org.kiworkshop.ibatis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kiworkshop.config.DataSourceConfig;
import org.kiworkshop.config.IBatisConfig;
import org.kiworkshop.dao.MemberDao;
import org.kiworkshop.domain.Member;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class MemberDaoIBatisImplTest {
    private MemberDao memberDao;

    @BeforeEach
    void setUp() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DataSourceConfig.class, IBatisConfig.class);
        memberDao = applicationContext.getBean("memberDaoIBatisImpl", MemberDao.class);
    }

    @AfterEach
    void tearDown() {
        memberDao.deleteAll();
    }

    @Test
    void createMember() {
        Member member = new Member(1L, "장재주", 1.0);

        memberDao.insert(member);

        Member foundMember = memberDao.findById(member.getId());

        assertThat(foundMember.getName()).isEqualTo("장재주");
        assertThat(foundMember.getPoint()).isEqualTo(1.0);
    }

    @Test
    void updateMember() {
        Member member = new Member(1L, "장재주", 1.0);
        memberDao.insert(member);

        Member foundMember = memberDao.findById(member.getId());

        assertThat(foundMember.getName()).isEqualTo("장재주");
        assertThat(foundMember.getPoint()).isEqualTo(1.0);

        foundMember.changeName("김덕수");
        memberDao.update(foundMember);

        Member updatedMember = memberDao.findById(foundMember.getId());

        assertThat(updatedMember.getName()).isEqualTo("김덕수");
        assertThat(updatedMember.getPoint()).isEqualTo(1.0);
    }
}