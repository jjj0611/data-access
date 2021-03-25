package org.kiworkshop.dao;

import org.kiworkshop.domain.Member;

import java.util.List;

public interface MemberDao {
    Long insert(Member member);

    void update(Member member);

    void delete(Member member);

    void deleteAll();

    Member findById(Long id);

    List<Member> findAll();
}
