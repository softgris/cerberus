package com.softgris.cerberus.service;

import com.softgris.cerberus.pojo.AddressPojo;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    int saveAddress(AddressPojo address);
    Optional<AddressPojo> getAddress(Integer id);
    List<AddressPojo> getAllAddresses();
    int deleteAddress(Integer id);
}
