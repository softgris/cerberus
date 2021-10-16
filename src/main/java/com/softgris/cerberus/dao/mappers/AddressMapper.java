package com.softgris.cerberus.dao.mappers;

import com.softgris.cerberus.pojo.AddressPojo;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class AddressMapper implements RowMapper<AddressPojo> {

    @Override
    public AddressPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
        AddressPojo address = new AddressPojo();

        address.setUserId(rs.getBigDecimal("user_id").toBigInteger());
        address.setCountry(rs.getString("country"));
        address.setCounty(rs.getString("county"));
        address.setCity(rs.getString("city"));
        address.setPostalCode(rs.getString("postal_code"));
        address.setLine1(rs.getString("line_1"));
        address.setLine2(rs.getString("line_2"));

        return address;
    }
}
