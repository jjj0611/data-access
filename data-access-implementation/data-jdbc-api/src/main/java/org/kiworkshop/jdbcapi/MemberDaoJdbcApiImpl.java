package org.kiworkshop.jdbcapi;

import org.kiworkshop.dao.MemberDao;
import org.kiworkshop.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoJdbcApiImpl implements MemberDao {
    private DataSource dataSource;

    public MemberDaoJdbcApiImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Member member) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO member (id, name, point) VALUES (?, ?, ?)")
        ) {
            preparedStatement.setLong(1, member.getId());
            preparedStatement.setString(2, member.getName());
            preparedStatement.setDouble(3, member.getPoint());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void update(Member member) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE member SET name=?, point=? WHERE id=?")
        ) {
            preparedStatement.setString(1, member.getName());
            preparedStatement.setDouble(2, member.getPoint());
            preparedStatement.setLong(3, member.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(Member member) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM member WHERE id=?")
        ) {
            preparedStatement.setLong(1, member.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteAll() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM member")
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Member findById(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM member WHERE id=?")
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long idFromDB = resultSet.getLong(1);
                String name = resultSet.getString(2);
                Double point = resultSet.getDouble(3);
                return new Member(idFromDB, name, point);
            }
            throw new RuntimeException("멤버가 존재하지 않습니다");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Member> findAll() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM member")
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Member> members = new ArrayList<>();
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                Double point = resultSet.getDouble(3);
                members.add(new Member(id, name, point));
            }
            return members;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }
}
