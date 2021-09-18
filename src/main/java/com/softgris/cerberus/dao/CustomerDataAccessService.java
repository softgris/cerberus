package com.softgris.cerberus.dao;

import com.softgris.cerberus.pojo.CustomerPojo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.math.BigInteger;

@Repository
public class CustomerDataAccessService implements CustomerDao {

    private RowMapper customerMapper;
    private JdbcTemplate jdbcTemplate;

    public CustomerDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        customerMapper = new CustomerMapper();
    }

    @Override
    public int saveCustomer(CustomerPojo customer) {

        String query = "INSERT INTO customer VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(query, customer.getCustomerId(), customer.getAddressId(), customer.getEmail(), customer.getPasswordHash());

//        String query = "INSERT INTO customer VALUES (" + customer.getUserId() +
//                ", " + customer.getAddressId() + ", " + customer.getEmail() +
//                ", " + customer.getPasswordHash() + ")";
//        return jdbcTemplate.update(query);
    }

    @Override
    public Optional<CustomerPojo> getCustomer(Integer id) {
        String query = "SELECT * FROM customer WHERE customer_id = ?";

        List<CustomerPojo> result = jdbcTemplate.query(query, customerMapper, id);

        return result.stream().findFirst();
    }

    public List<CustomerPojo> getAllCustomers() {
        String query = "SELECT * FROM customer";
        return jdbcTemplate.query(query, customerMapper);
    }

    @Override
    public int deleteCustomer(Integer id) {
        String query = "DELETE FROM customer WHERE customer_id = ?";
        return jdbcTemplate.update(query, id);
    }

    public int updateCustomer(CustomerPojo customer) {
//        String query = "UPDATE customer SET customer_id = " + customer.getAddressId() +
//                ", email = " + customer.getEmail() + ", password_hash = " + customer.getPasswordHash() +
//                " where customer_id = " + customer.getCustomer();
//        return jdbcTemplate.update(query);

        String query = "UPDATE customer SET (?, ?, ?) WHERE customer_id = ?";
        return jdbcTemplate.update(query, customer.getAddressId(), customer.getEmail(), customer.getPasswordHash(), customer.getCustomerId());
    }

}
