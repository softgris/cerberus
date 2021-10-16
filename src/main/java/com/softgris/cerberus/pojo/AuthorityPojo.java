package com.softgris.cerberus.pojo;

import java.math.BigInteger;

public class AuthorityPojo {

    private BigInteger authorityId;
    private String authority;
    private String description;

    public BigInteger getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(BigInteger authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
