package com.softgris.cerberus.controller;

import com.softgris.cerberus.pojo.AddressPojo;
import com.softgris.cerberus.pojo.CustomerPojo;
import com.softgris.cerberus.service.AddressService;
import com.softgris.cerberus.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public int saveCustomer(@RequestBody CustomerPojo customer) {
        return customerService.saveCustomer(customer);
    }

    @GetMapping("{id}")
    public Optional<CustomerPojo> getCustomer(@PathVariable("id") Integer id) {
        return customerService.getCustomer(id);
    }

    @GetMapping
    public List<CustomerPojo> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @DeleteMapping("{id}")
    public int deleteCustomer(@PathVariable("id") Integer id) {
        return customerService.deleteCustomer(id);
    }
}
