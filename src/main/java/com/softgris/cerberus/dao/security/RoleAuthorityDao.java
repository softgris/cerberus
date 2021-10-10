package com.softgris.cerberus.dao.security;

import com.softgris.cerberus.pojo.RoleAuthorityPojo;
import java.math.BigInteger;
import java.util.List;

public interface RoleAuthorityDao {

    int saveRoleAuthority(RoleAuthorityPojo roleAuthority);

    List<RoleAuthorityPojo> getAllRoleAuthority();

    List<RoleAuthorityPojo> getAllAuthoritiesForRole(BigInteger roleId);

    int deleteRoleAuthority(BigInteger roleId, BigInteger authorityId);
}
