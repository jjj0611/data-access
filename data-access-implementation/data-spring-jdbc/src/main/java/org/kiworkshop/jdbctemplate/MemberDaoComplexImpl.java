package org.kiworkshop.jdbctemplate;

import org.kiworkshop.dao.MemberDao;
import org.kiworkshop.domain.Member;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class MemberDaoComplexImpl implements MemberDao {
    private final SimpleJdbcTemplate simpleJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public MemberDaoComplexImpl(DataSource dataSource) {
        this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("member");
    }

    @Override
    public void insert(Member member) {
        simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(member));
    }

    @Override
    public void update(Member member) {
        // TODO: complex하게 구성한 Dao 작성해보기
    }

    @Override
    public void delete(Member member) {
        // TODO: complex하게 구성한 Dao 작성해보기
    }

    @Override
    public void deleteAll() {
        // TODO: complex하게 구성한 Dao 작성해보기
    }

    @Override
    public Member findById(Long id) {
        // TODO: complex하게 구성한 Dao 작성해보기
        return null;
    }

    @Override
    public List<Member> findAll() {
        // TODO: complex하게 구성한 Dao 작성해보기
        return null;
    }
}
