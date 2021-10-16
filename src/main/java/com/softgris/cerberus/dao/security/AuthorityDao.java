package com.softgris.cerberus.dao.security;

import com.softgris.cerberus.pojo.AuthorityPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface AuthorityDao {

    int saveAuthority(AuthorityPojo authority);

    Optional<AuthorityPojo> getAuthority(BigInteger authorityId);

    List<AuthorityPojo> getAllAuthorities();

    int deleteAuthority(BigInteger authorityId);

    int updateAuthority(AuthorityPojo authority);
}
