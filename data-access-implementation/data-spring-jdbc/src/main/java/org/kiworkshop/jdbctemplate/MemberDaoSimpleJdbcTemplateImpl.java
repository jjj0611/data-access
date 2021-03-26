package org.kiworkshop.jdbctemplate;

import org.kiworkshop.dao.MemberDao;
import org.kiworkshop.domain.Member;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import java.util.List;

public class MemberDaoSimpleJdbcTemplateImpl implements MemberDao {
    private final SimpleJdbcTemplate simpleJdbcTemplate;

    public MemberDaoSimpleJdbcTemplateImpl(SimpleJdbcTemplate simpleJdbcTemplate) {
        this.simpleJdbcTemplate = simpleJdbcTemplate;
    }

    @Override
    public void insert(Member member) {
        MapSqlParameterSource source = new MapSqlParameterSource()
            .addValue("id", member.getId())
            .addValue("name", member.getName())
            .addValue("point", member.getPoint());
        simpleJdbcTemplate.update("INSERT INTO MEMBER (ID, NAME, POINT, args) VALUES (:id, :name, :point)", source);
    }

    @Override
    public void update(Member member) {
        BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(member);
        simpleJdbcTemplate.update("UPDATE MEMBER SET ID=:id, NAME=:name, POINT=:point", source);
    }

    @Override
    public void delete(Member member) {
        simpleJdbcTemplate.update("DELETE FROM MEMBER WHERE ID=:id", member.getId());
    }

    @Override
    public void deleteAll() {
        simpleJdbcTemplate.update("DELETE FROM MEMBER");
    }

    @Override
    public Member findById(Long id) {
        return simpleJdbcTemplate.queryForObject("SELECT * FROM MEMBER WHERE ID=:id", Member.class, id);
    }

    @Override
    public List<Member> findAll() {
        RowMapper<Member> rm = (rs, rowNum) -> {
            Long id = rs.getLong("ID");
            String name = rs.getString("NAME");
            Double point = rs.getDouble("POINT");
            return new Member(id, name, point);
        };
        simpleJdbcTemplate.query("SELECT * FROM MEMBER", rm);
        return null;
    }
}
