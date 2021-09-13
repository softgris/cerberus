package com.softgris.cerberus.controller;

import com.softgris.cerberus.service.AddressService;
import org.springframework.web.bind.annotation.*;

import com.softgris.cerberus.pojo.AddressPojo;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {

    AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public int saveAddress(@RequestBody AddressPojo address) {
        return addressService.saveAddress(address);
    }

    @GetMapping("{id}")
    public Optional<AddressPojo> getAddress(@PathVariable("id") Integer id) {
        return addressService.getAddress(id);
    }

    @GetMapping
    public List<AddressPojo> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @DeleteMapping("{id}")
    public int deleteAddress(@PathVariable("id") Integer id) {
        return addressService.deleteAddress(id);
    }
}
