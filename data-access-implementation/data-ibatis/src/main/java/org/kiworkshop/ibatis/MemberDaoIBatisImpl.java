package org.kiworkshop.ibatis;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.kiworkshop.dao.MemberDao;
import org.kiworkshop.domain.Member;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import java.util.List;

public class MemberDaoIBatisImpl implements MemberDao {
    private SqlMapClientTemplate sqlMapClientTemplate;

    public MemberDaoIBatisImpl(SqlMapClient sqlMapClient) {
        this.sqlMapClientTemplate = new SqlMapClientTemplate(sqlMapClient);
    }

    @Override
    public void insert(Member member) {
        sqlMapClientTemplate.insert("insertMember", member);
    }

    @Override
    public void update(Member member) {
        sqlMapClientTemplate.update("updateMember", member);
    }

    @Override
    public void delete(Member member) {
        sqlMapClientTemplate.delete("deleteMember", member);
    }

    @Override
    public void deleteAll() {
        sqlMapClientTemplate.delete("deleteAllMember", null);
    }

    @Override
    public Member findById(Long id) {
        return (Member) sqlMapClientTemplate.queryForObject("findMemberById", id);
    }

    @Override
    public List<Member> findAll() {
        return sqlMapClientTemplate.queryForList("findAllMember", null);
    }
}
