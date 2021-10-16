package com.softgris.cerberus.dao.mappers;

import com.softgris.cerberus.pojo.RoleAuthorityPojo;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class RoleAuthorityMapper implements RowMapper<RoleAuthorityPojo> {

    @Override
    public RoleAuthorityPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
        RoleAuthorityPojo roleAuthority = new RoleAuthorityPojo();
        roleAuthority.setRoleId(rs.getBigDecimal("role_id").toBigInteger());
        roleAuthority.setAuthorityId(rs.getBigDecimal("authority_id").toBigInteger());

        return roleAuthority;
    }
}
