package com.softgris.cerberus.dao;

import com.softgris.cerberus.dto.AddressDto;
import com.softgris.cerberus.pojo.AddressPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDataAccessService implements AddressDao {

    private final JdbcTemplate jdbcTemplate;
    private final AddressMapper addressMapper;

    public AddressDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.addressMapper = new AddressMapper();
    }

    public int saveAddress(BigInteger addressId, AddressDto address) {
        String query = "INSERT INTO address VALUES (?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(query,
            addressId,
            address.getCountry(),
            address.getCounty(),
            address.getPostalCode(),
            address.getLine1(),
            address.getLine2());
    }

    public Optional<AddressPojo> getAddress(BigInteger addressId) {
        String query = "SELECT * FROM address WHERE address_id = ?";
        List<AddressPojo> result = jdbcTemplate.query(query, addressMapper, addressId);

        return result.stream().findFirst();
    }

    public List<AddressPojo> getAllAddresses() {
        String query = "SELECT * FROM address";
        return jdbcTemplate.query(query, new AddressMapper());
    }

    public int deleteAddress(BigInteger addressId) {
        String query = "DELETE FROM address WHERE address_id = ?";
        return jdbcTemplate.update(query, addressId);
    }

    public int updateAddress(BigInteger addressId, AddressDto address) {
        String query = "UPDATE address SET country=?, county=?, postal_code=?, line_1=?, line_2=?" +
            " WHERE address_id=?";
        return jdbcTemplate.update(query,
            address.getCountry(),
            address.getCounty(),
            address.getPostalCode(),
            address.getLine1(),
            address.getLine2(),
            addressId);
    }
}
