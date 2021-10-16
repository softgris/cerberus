package com.softgris.cerberus.dao.mappers;

import com.softgris.cerberus.pojo.UserPojo;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<UserPojo> {

    @Override
    public UserPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserPojo user = new UserPojo();
        user.setUserId(rs.getBigDecimal("user_id").toBigInteger());
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRoleId(rs.getBigDecimal("role_id").toBigInteger());
        user.setEnabled(rs.getBoolean("enabled"));

        return user;
    }
}
