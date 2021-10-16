package com.softgris.cerberus.dao.user;

import com.softgris.cerberus.dao.mappers.AddressMapper;
import com.softgris.cerberus.pojo.AddressPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDaoImpl implements AddressDao {

    private final RowMapper<AddressPojo> addressMapper;
    private final JdbcTemplate jdbcTemplate;

    public AddressDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        addressMapper = new AddressMapper();
    }

    @Override
    public int saveAddress(AddressPojo address) {
        String query = "INSERT INTO cerberus.address(user_id, country, county, city, postal_code, line_1, line_2) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(query,
            address.getUserId(),
            address.getCountry(),
            address.getCounty(),
            address.getCity(),
            address.getPostalCode(),
            address.getLine1(),
            address.getLine2());
    }

    @Override
    public Optional<AddressPojo> getAddress(BigInteger userId) {
        String query = "SELECT * FROM address WHERE user_id = ?";
        List<AddressPojo> result = jdbcTemplate.query(query, addressMapper, userId);
        return result.stream().findFirst();
    }

    @Override
    public List<AddressPojo> getAllAddresses() {
        String query = "SELECT * FROM address";
        return jdbcTemplate.query(query, addressMapper);
    }

    @Override
    public int deleteAddress(BigInteger userId) {
        String query = "DELETE FROM address WHERE user_id = ?";
        return jdbcTemplate.update(query, userId);
    }

    @Override
    public int updateAddress(AddressPojo address) {
        String query = "UPDATE address(country, county, city, postal_code, line_1, line_2) SET (?, ?, ?, ?, ?, ?) WHERE user_id = ?";
        return jdbcTemplate.update(query,
            address.getCountry(),
            address.getCounty(),
            address.getCity(),
            address.getPostalCode(),
            address.getLine1(),
            address.getLine2(),
            address.getUserId());
    }
}
