package org.kiworkshop.jdbctemplate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kiworkshop.config.DataSourceConfig;
import org.kiworkshop.config.JdbcTemplateConfig;
import org.kiworkshop.dao.MemberDao;
import org.kiworkshop.domain.Member;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class batchTest {
    private MemberDaoSimpleJdbcTemplateImpl memberDaoSimpleJdbcTemplate;
    private MemberDaoSimpleJdbcInsertImpl memberDaoSimpleJdbcInsertImpl;

    @BeforeEach
    void setUp() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DataSourceConfig.class, JdbcTemplateConfig.class);
        memberDaoSimpleJdbcInsertImpl = applicationContext.getBean("memberDaoSimpleJdbcInsertImpl", MemberDaoSimpleJdbcInsertImpl.class);
        memberDaoSimpleJdbcTemplate = applicationContext.getBean("memberDaoSimpleJdbcTemplateImpl", MemberDaoSimpleJdbcTemplateImpl.class);
    }

    @AfterEach
    void tearDown() {
        memberDaoSimpleJdbcTemplate.deleteAll();
    }

    @Test
    void batchTest() {
        List<Member> members = LongStream.rangeClosed(1, 100)
            .mapToObj(id -> new Member(id, "장재주", id + 5.0))
            .collect(Collectors.toList());

        long jdbcTemplateStartTime = System.currentTimeMillis();
        memberDaoSimpleJdbcTemplate.insertAll(members);
        System.out.println("jdbc template took " + (System.currentTimeMillis() - jdbcTemplateStartTime) + "ms");

        memberDaoSimpleJdbcTemplate.deleteAll();

        long jdbcInsertStartTime = System.currentTimeMillis();
        memberDaoSimpleJdbcInsertImpl.insertAll(members);
        System.out.println("jdbc insert took " + (System.currentTimeMillis() - jdbcInsertStartTime) + "ms");
    }

    @Test
    void jdbcTemplateInsertAll() {
        List<Member> members = LongStream.rangeClosed(1, 100)
            .mapToObj(id -> new Member(id, "장재주", id + 5.0))
            .collect(Collectors.toList());

        long jdbcTemplateStartTime = System.currentTimeMillis();
        memberDaoSimpleJdbcTemplate.insertAll(members);
        System.out.println("jdbc template took " + (System.currentTimeMillis() - jdbcTemplateStartTime) + "ms");
    }

    @Test
    void jdbcInsertAll() {
        List<Member> members = LongStream.rangeClosed(1, 100)
            .mapToObj(id -> new Member(id, "장재주", id + 5.0))
            .collect(Collectors.toList());

        long jdbcInsertStartTime = System.currentTimeMillis();
        memberDaoSimpleJdbcInsertImpl.insertAll(members);
        System.out.println("jdbc insert took " + (System.currentTimeMillis() - jdbcInsertStartTime) + "ms");
    }

    @Test
    void deleteAll() {
        memberDaoSimpleJdbcTemplate.deleteAll();
    }
}
