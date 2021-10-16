package com.softgris.cerberus.dao.security;

import com.softgris.cerberus.pojo.RolePojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface RoleDao {

    int saveRole(RolePojo role);

    Optional<RolePojo> getRole(BigInteger roleId);

    List<RolePojo> getAllRoles();

    int deleteRole(BigInteger roleId);

    int updateRole(RolePojo role);
}
