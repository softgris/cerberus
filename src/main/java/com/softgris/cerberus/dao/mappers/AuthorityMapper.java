package com.softgris.cerberus.dao.mappers;

import com.softgris.cerberus.pojo.AuthorityPojo;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class AuthorityMapper implements RowMapper<AuthorityPojo> {

    @Override
    public AuthorityPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
        AuthorityPojo authority = new AuthorityPojo();

        authority.setAuthorityId(rs.getBigDecimal("authority_id").toBigInteger());
        authority.setAuthority(rs.getString("authority"));
        authority.setDescription(rs.getString("description"));

        return authority;
    }
}
