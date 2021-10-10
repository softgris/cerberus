package com.softgris.cerberus.dao.security;

import com.softgris.cerberus.dao.mappers.RoleAuthorityMapper;
import com.softgris.cerberus.pojo.RoleAuthorityPojo;
import java.math.BigInteger;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class RoleAuthorityDaoImpl implements RoleAuthorityDao {

    private final RowMapper<RoleAuthorityPojo> roleAuthorityMapper;
    private final JdbcTemplate jdbcTemplate;

    public RoleAuthorityDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        roleAuthorityMapper = new RoleAuthorityMapper();
    }

    @Override
    public int saveRoleAuthority(RoleAuthorityPojo roleAuthority) {
        String query = "INSERT INTO role_authority(role_id, authority_id) VALUES (?, ?)";
        return jdbcTemplate.update(query,
            roleAuthority.getRoleId(),
            roleAuthority.getAuthorityId());
    }

    @Override
    public List<RoleAuthorityPojo> getAllRoleAuthority() {
        String query = "SELECT * FROM role_authority";
        return jdbcTemplate.query(query, roleAuthorityMapper);
    }

    @Override
    public List<RoleAuthorityPojo> getAllAuthoritiesForRole(BigInteger roleId) {
        String query = "SELECT * FROM role_authority WHERE role_id = ?";
        return jdbcTemplate.query(query, roleAuthorityMapper, roleId);
    }

    @Override
    public int deleteRoleAuthority(BigInteger roleId, BigInteger authorityId) {
        String query = "DELETE FROM role_authority WHERE role_id = ? AND authority_id = ?";
        return jdbcTemplate.update(query, roleId, authorityId);
    }
}
