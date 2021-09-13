package com.softgris.cerberus.dao;

import com.softgris.cerberus.pojo.AddressPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public class AddressDataAccessService implements AddressDao {
    private JdbcTemplate jdbcTemplate;

    // Will be used later
    @Autowired
    private DataSource dataSource;
    private PlatformTransactionManager platformTransactionManager;

    public AddressDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveAddress(AddressPojo address) {

        String query = "INSERT INTO address VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(query, address.getAddressId(), address.getCountry(), address.getCounty(),
                address.getPostalCode(), address.getLine1(), address.getLine2());

//        String query = "INSERT INTO user VALUES (" + user.getUserId() +
//                ", " + user.getAddressId() + ", " + user.getEmail() +
//                ", " + user.getPasswordHash() + ")";
//        return jdbcTemplate.update(query);
    }

    public Optional<AddressPojo> getAddress(int i) {
        String query = "SELECT * FROM address WHERE address_id = ?";
        List<AddressPojo> result = jdbcTemplate.query(query, new AddressMapper(), i);

        return result.stream().findFirst();
    }

    public List<AddressPojo> getAllAddresses() {
        String query = "SELECT * FROM address";
        return jdbcTemplate.query(query, new AddressMapper());
    }

    public int deleteAddress(int i) {
        String query = "DELETE FROM address WHERE address_id = ?";
        return jdbcTemplate.update(query, i);
    }
}
