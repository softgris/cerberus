package com.softgris.cerberus.service;

import com.softgris.cerberus.pojo.AddressPojo;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    public int saveAddress(AddressPojo address);
    public Optional<AddressPojo> getAddress(Integer i);
    public List<AddressPojo> getAllAddresses();
    public int deleteAddress(Integer address);
}
