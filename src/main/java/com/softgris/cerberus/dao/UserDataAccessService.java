package com.softgris.cerberus.dao;

import com.softgris.cerberus.pojo.UserPojo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.math.BigInteger;

@Repository
public class UserDataAccessService implements UserDao {

    private RowMapper userMapper;
    private JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        userMapper = new UserMapper();
    }

    @Override
    public int saveUser(UserPojo user) {

        String query = "INSERT INTO user VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(query, user.getUserId(), user.getAddressId(), user.getEmail(), user.getPasswordHash());

//        String query = "INSERT INTO user VALUES (" + user.getUserId() +
//                ", " + user.getAddressId() + ", " + user.getEmail() +
//                ", " + user.getPasswordHash() + ")";
//        return jdbcTemplate.update(query);
    }

    @Override
    public Optional<UserPojo> getUser(Integer id) {
        String query = "SELECT * FROM user WHERE user_id = ?";

        List<UserPojo> result = jdbcTemplate.query(query, userMapper, id);

        return result.stream().findFirst();
    }

    @Override
    public List<UserPojo> getAllUsers() {
        String query = "SELECT * FROM user";
        return jdbcTemplate.query(query, userMapper);
    }

    @Override
    public int deleteUser(Integer id) {
        String query = "DELETE FROM user WHERE user_id = ?";
        return jdbcTemplate.update(query, id);
    }

    public int updateUser(UserPojo user) {
//        String query = "UPDATE user SET address_id = " + user.getAddressId() +
//                ", email = " + user.getEmail() + ", password_hash = " + user.getPasswordHash() +
//                " where user_id = " + user.getUserId();
//        return jdbcTemplate.update(query);

        String query = "UPDATE user SET (?, ?, ?) WHERE user_id = ?";
        return jdbcTemplate.update(query, user.getAddressId(), user.getEmail(), user.getPasswordHash(), user.getUserId());
    }

}
