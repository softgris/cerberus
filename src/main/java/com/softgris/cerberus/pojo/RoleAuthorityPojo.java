package com.softgris.cerberus.pojo;

import java.math.BigInteger;

public class RoleAuthorityPojo {

    BigInteger roleId;
    BigInteger authorityId;

    public BigInteger getRoleId() {
        return roleId;
    }

    public void setRoleId(BigInteger roleId) {
        this.roleId = roleId;
    }

    public BigInteger getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(BigInteger authorityId) {
        this.authorityId = authorityId;
    }
}
