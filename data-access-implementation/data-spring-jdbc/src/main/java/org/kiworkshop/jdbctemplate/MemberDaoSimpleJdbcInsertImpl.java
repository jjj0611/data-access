package org.kiworkshop.jdbctemplate;

import org.kiworkshop.dao.MemberDao;
import org.kiworkshop.domain.Member;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

public class MemberDaoSimpleJdbcInsertImpl implements MemberDao {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public MemberDaoSimpleJdbcInsertImpl(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("member");
    }

    @Override
    public void insert(Member member) {
        SqlParameterSource source = new MapSqlParameterSource()
            .addValue("id", member.getId())
            .addValue("name", member.getName())
            .addValue("point", member.getPoint());
        simpleJdbcInsert.execute(source);
    }

    public void insertAll(List<Member> members) {
        List<MapSqlParameterSource> sources = members.stream()
            .map(member -> new MapSqlParameterSource()
                .addValue("id", member.getId())
                .addValue("name", member.getName())
                .addValue("point", member.getPoint())
            ).collect(Collectors.toList());
        simpleJdbcInsert.executeBatch(sources.toArray(new MapSqlParameterSource[0]));
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
