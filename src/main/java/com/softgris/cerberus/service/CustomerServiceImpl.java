package com.softgris.cerberus.service;

import com.softgris.cerberus.dao.CustomerDao;
import com.softgris.cerberus.pojo.CustomerPojo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public int saveCustomer(CustomerPojo customer) {
        return customerDao.saveCustomer(customer);
    }

    @Override
    public Optional<CustomerPojo> getCustomer(Integer id) {
        return customerDao.getCustomer(id);
    }

    @Override
    public List<CustomerPojo> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public int deleteCustomer(Integer id) {
        return customerDao.deleteCustomer(id);
    }
}
