package org.kiworkshop.jdbctemplate;

import org.kiworkshop.dao.MemberDao;
import org.kiworkshop.domain.Member;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.List;

public class MemberDaoSimpleJdbcInsertImpl implements MemberDao {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public MemberDaoSimpleJdbcInsertImpl(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
    }

    @Override
    public void insert(Member member) {

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
