package com.softgris.cerberus.controller;

import com.softgris.cerberus.pojo.AddressPojo;
import com.softgris.cerberus.service.AddressService;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public AddressPojo saveAddress(@RequestBody AddressPojo address) {
        return addressService.saveAddress(address);
    }

    @GetMapping("{addressId}")
    public Optional<AddressPojo> getAddress(@PathVariable("addressId") BigInteger addressId) {
        return addressService.getAddress(addressId);
    }

    @GetMapping
    public List<AddressPojo> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @DeleteMapping("{addressId}")
    public void deleteAddress(@PathVariable("addressId") BigInteger addressId) {
        addressService.deleteAddress(addressId);
    }
}
