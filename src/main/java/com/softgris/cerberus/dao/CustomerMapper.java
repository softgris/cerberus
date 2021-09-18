package com.softgris.cerberus.dao;

import com.softgris.cerberus.pojo.CustomerPojo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper {
    @Override
    public CustomerPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomerPojo customer = new CustomerPojo();
        customer.setCustomerId(rs.getBigDecimal("customer_id").toBigInteger());
        customer.setAddressId(rs.getBigDecimal("address_id").toBigInteger());
        customer.setEmail(rs.getString("email"));
        customer.setPasswordHash(rs.getString("password_hash"));

        return customer;
    }
}
