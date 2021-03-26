package org.kiworkshop.jdbctemplate;

import org.kiworkshop.dao.MemberDao;
import org.kiworkshop.domain.Member;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class MemberDaoSimpleJdbcTemplateImpl implements MemberDao {
    private final SimpleJdbcTemplate simpleJdbcTemplate;

    public MemberDaoSimpleJdbcTemplateImpl(DataSource dataSource) {
        this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    @Override
    public void insert(Member member) {
        MapSqlParameterSource source = new MapSqlParameterSource()
            .addValue("id", member.getId())
            .addValue("name", member.getName())
            .addValue("point", member.getPoint());
        simpleJdbcTemplate.update("INSERT INTO member (id, name, point) VALUES (:id, :name, :point)", source);
    }

    public void insertAll(List<Member> members) {
        for (Member member : members) {
            insert(member);
        }
    }

    @Override
    public void update(Member member) {
        BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(member);
        simpleJdbcTemplate.update("UPDATE member SET id=:id, name=:name, point=:point", source);
    }

    @Override
    public void delete(Member member) {
        MapSqlParameterSource source = new MapSqlParameterSource()
            .addValue("id", member.getId());
        simpleJdbcTemplate.update("DELETE FROM member WHERE id=:id", source);
    }

    @Override
    public void deleteAll() {
        simpleJdbcTemplate.update("DELETE FROM member");
    }

    @Override
    public Member findById(Long id) {
        MapSqlParameterSource source = new MapSqlParameterSource()
            .addValue("id", id);
        RowMapper<Member> rm = (rs, rowNum) -> {
            Long idFromDb = rs.getLong(1);
            String name = rs.getString(2);
            Double point = rs.getDouble(3);
            return new Member(idFromDb, name, point);
        };
        return simpleJdbcTemplate.queryForObject("SELECT * FROM member WHERE id=:id", rm, source);
    }

    @Override
    public List<Member> findAll() {
        RowMapper<Member> rm = (rs, rowNum) -> {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            Double point = rs.getDouble("point");
            return new Member(id, name, point);
        };
        simpleJdbcTemplate.query("SELECT * FROM member", rm);
        return null;
    }
}
