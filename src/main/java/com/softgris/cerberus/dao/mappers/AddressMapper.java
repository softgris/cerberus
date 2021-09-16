package com.softgris.cerberus.dao.mappers;

import com.softgris.cerberus.pojo.AddressPojo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressMapper implements RowMapper {

    @Override
    public AddressPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
        AddressPojo address = new AddressPojo();
        address.setAddressId(rs.getBigDecimal("address_id").toBigInteger());
        address.setCountry(rs.getString("country"));
        address.setCounty(rs.getString("county"));
        address.setPostalCode(rs.getString("postal_code"));
        address.setLine1(rs.getString("line_1"));
        address.setLine2(rs.getString("line_2"));

        return address;
    }
}
