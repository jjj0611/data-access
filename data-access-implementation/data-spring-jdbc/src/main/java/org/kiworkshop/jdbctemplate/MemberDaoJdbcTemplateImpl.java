package org.kiworkshop.jdbctemplate;

import org.kiworkshop.dao.MemberDao;
import org.kiworkshop.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

public class MemberDaoJdbcTemplateImpl implements MemberDao {
    private final JdbcTemplate jdbcTemplate;

    public MemberDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long insert(Member member) {
        return null;
    }

    @Override
    public void update(Member member) {

    }

    @Override
    public void delete(Member member) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Member findById(Long id) {
        return null;
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}