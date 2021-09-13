package com.softgris.cerberus.dao;

import com.softgris.cerberus.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.*;
import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserPojo getUser(BigInteger userId) {
        String query = "SELECT * FROM user";

        ResultSet rs = jdbcTemplate.query(query, new ResultSetExtractor<ResultSet>() {
            @Override
            public ResultSet extractData(ResultSet rs) throws SQLException, DataAccessException {
                return rs;
            }
        });

        UserPojo user = new UserPojo();

        return user;
    }

    public List<UserPojo> getAllUsers() {
        String query = "SELECT * FROM user";
        return jdbcTemplate.query(query, new UserMapper());
    }

    public int saveUser(UserPojo user) {

        String query = "INSERT INTO user VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(query, user.getUserId(), user.getAddressId(), user.getEmail(), user.getPasswordHash());

//        String query = "INSERT INTO user VALUES (" + user.getUserId() +
//                ", " + user.getAddressId() + ", " + user.getEmail() +
//                ", " + user.getPasswordHash() + ")";
//        return jdbcTemplate.update(query);
    }

    public int updateUser(UserPojo user) {
//        String query = "UPDATE user SET address_id = " + user.getAddressId() +
//                ", email = " + user.getEmail() + ", password_hash = " + user.getPasswordHash() +
//                " where user_id = " + user.getUserId();
//        return jdbcTemplate.update(query);

        String query = "UPDATE user SET (?, ?, ?) WHERE user_id = ?";
        return jdbcTemplate.update(query, user.getAddressId(), user.getEmail(), user.getPasswordHash(), user.getUserId());
    }

    public int deleteUser(UserPojo user) {
//        String query = "DELETE FROM user WHERE user_id = " + user.getUserId();
//        return jdbcTemplate.update(query);

        String query = "DELETE FROM user WHERE user_id = ?";
        return jdbcTemplate.update(query, user.getUserId());
    }
}
