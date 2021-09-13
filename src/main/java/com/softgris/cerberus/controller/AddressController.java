package com.softgris.cerberus.controller;

import com.softgris.cerberus.dto.AddressDto;
import com.softgris.cerberus.service.AddressService;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public BigInteger saveAddress(@RequestBody AddressDto address) {
        return addressService.saveAddress(address);
    }

    @GetMapping("{addressId}")
    public AddressDto getAddress(@PathVariable("addressId") BigInteger addressId) {
        return addressService.getAddress(addressId).map(AddressDto::new).orElse(null);
    }

    @GetMapping
    public List<AddressDto> getAllAddresses() {
        return addressService.getAllAddresses().stream()
            .map(AddressDto::new)
            .collect(Collectors.toList());
    }

    @DeleteMapping("{addressId}")
    public boolean deleteAddress(@PathVariable("addressId") BigInteger addressId) {
        return addressService.deleteAddress(addressId);
    }

    @PutMapping("{addressId}")
    public boolean updateAddress(@PathVariable("addressId") BigInteger addressId,
        @RequestBody AddressDto address) {
        return addressService.updateAddress(addressId, address);
    }
}
