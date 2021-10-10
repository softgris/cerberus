package com.softgris.cerberus.dao.mappers;

import com.softgris.cerberus.pojo.RolePojo;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class RoleMapper implements RowMapper<RolePojo> {

    @Override
    public RolePojo mapRow(ResultSet rs, int rowNum) throws SQLException {
        RolePojo role = new RolePojo();
        role.setRoleId(rs.getBigDecimal("role_id").toBigInteger());
        role.setRole(rs.getString("role"));
        role.setDescription(rs.getString("description"));
        return role;
    }
}
