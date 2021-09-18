package com.softgris.cerberus.dao;

import com.softgris.cerberus.dao.mappers.AddressMapper;
import com.softgris.cerberus.pojo.AddressPojo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AddressDaoImpl implements AddressDao {

    private RowMapper addressMapper;
    private JdbcTemplate jdbcTemplate;

    public AddressDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        addressMapper = new AddressMapper();
    }

    @Override
    public int saveAddress(AddressPojo address) {

        String query = "INSERT INTO address VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(query, address.getAddressId(), address.getCountry(), address.getCounty(),
                address.getPostalCode(), address.getLine1(), address.getLine2());

//        String query = "INSERT INTO user VALUES (" + user.getUserId() +
//                ", " + user.getAddressId() + ", " + user.getEmail() +
//                ", " + user.getPasswordHash() + ")";
//        return jdbcTemplate.update(query);
    }

    @Override
    public Optional<AddressPojo> getAddress(int i) {
        String query = "SELECT * FROM address WHERE address_id = ?";
        List<AddressPojo> result = jdbcTemplate.query(query, addressMapper, i);

        return result.stream().findFirst();
    }

    @Override
    public List<AddressPojo> getAllAddresses() {
        String query = "SELECT * FROM address";
        return jdbcTemplate.query(query, addressMapper);
    }

    @Override
    public int deleteAddress(int i) {
        String query = "DELETE FROM address WHERE address_id = ?";
        return jdbcTemplate.update(query, i);
    }
}
