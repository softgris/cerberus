package com.softgris.cerberus.dao;

import com.softgris.cerberus.pojo.UserPojo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper {
    @Override
    public UserPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserPojo user = new UserPojo();
        user.setUserId(rs.getBigDecimal("user_id").toBigInteger());
        user.setAddressId(rs.getBigDecimal("address_id").toBigInteger());
        user.setEmail(rs.getString("email"));
        user.setPasswordHash(rs.getString("password_hash"));

        return user;
    }
}
