package com.softgris.cerberus.dao.security;

import com.softgris.cerberus.dao.mappers.AuthorityMapper;
import com.softgris.cerberus.pojo.AuthorityPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class AuthorityDaoImpl implements AuthorityDao {

    RowMapper<AuthorityPojo> authorityMapper;
    JdbcTemplate jdbcTemplate;

    public AuthorityDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.authorityMapper = new AuthorityMapper();
    }

    @Override
    public int saveAuthority(AuthorityPojo authority) {
        String query = "INSERT INTO authority(authority_id, authority, description) VALUES (?, ?, ?)";
        return jdbcTemplate.update(query,
            authority.getAuthorityId(),
            authority.getAuthority(),
            authority.getDescription());
    }

    @Override
    public Optional<AuthorityPojo> getAuthority(BigInteger authorityId) {
        String query = "SELECT * FROM authority WHERE authority_id = ?";
        List<AuthorityPojo> result = jdbcTemplate.query(query, authorityMapper, authorityId);
        return result.stream().findFirst();
    }

    @Override
    public List<AuthorityPojo> getAllAuthorities() {
        String query = "SELECT * FROM authority";
        return jdbcTemplate.query(query, authorityMapper);
    }

    @Override
    public int deleteAuthority(BigInteger authorityId) {
        String query = "DELETE FROM authority WHERE authority_id = ?";
        return jdbcTemplate.update(query, authorityId);
    }

    @Override
    public int updateAuthority(AuthorityPojo authority) {
        String query = "UPDATE authority(authority, description) SET (?, ?) WHERE authority_id = ?";
        return jdbcTemplate.update(query,
            authority.getAuthority(),
            authority.getDescription(),
            authority.getAuthorityId());
    }
}
