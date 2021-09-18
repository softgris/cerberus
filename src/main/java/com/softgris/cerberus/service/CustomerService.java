package com.softgris.cerberus.service;

import com.softgris.cerberus.pojo.CustomerPojo;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    int saveCustomer(CustomerPojo customer);
    Optional<CustomerPojo> getCustomer(Integer id);
    List<CustomerPojo> getAllCustomers();
    int deleteCustomer(Integer id);
}
