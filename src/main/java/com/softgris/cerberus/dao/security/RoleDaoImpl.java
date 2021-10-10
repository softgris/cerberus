package com.softgris.cerberus.dao.security;

import com.softgris.cerberus.dao.mappers.RoleMapper;
import com.softgris.cerberus.pojo.RolePojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class RoleDaoImpl implements RoleDao {

    private final RowMapper<RolePojo> roleMapper;
    private final JdbcTemplate jdbcTemplate;

    public RoleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.roleMapper = new RoleMapper();
    }

    @Override
    public int saveRole(RolePojo role) {
        String query = "INSERT INTO role(role_id, role, description) VALUES (?, ?, ?)";
        return jdbcTemplate.update(query,
            role.getRoleId(),
            role.getRole(),
            role.getDescription());
    }

    @Override
    public Optional<RolePojo> getRole(BigInteger roleId) {
        String query = "SELECT * FROM role WHERE role_id = ?";
        List<RolePojo> result = jdbcTemplate.query(query, roleMapper, roleId);
        return result.stream().findFirst();
    }

    @Override
    public List<RolePojo> getAllRoles() {
        String query = "SELECT * FROM role";
        return jdbcTemplate.query(query, roleMapper);
    }

    @Override
    public int deleteRole(BigInteger roleId) {
        String query = "DELETE FROM role WHERE role_id = ?";
        return jdbcTemplate.update(query, roleId);
    }

    @Override
    public int updateRole(RolePojo role) {
        String query = "UPDATE role(role, role_description) SET (?, ?) WHERE role_id = ?";
        return jdbcTemplate.update(query,
            role.getRole(),
            role.getDescription(),
            role.getRoleId());
    }
}
