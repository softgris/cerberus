package com.softgris.cerberus.dao;

import com.softgris.cerberus.pojo.AddressPojo;

import java.util.List;
import java.util.Optional;

public interface AddressDao {
    int saveAddress(AddressPojo address);
    Optional<AddressPojo> getAddress(int i);
    List<AddressPojo> getAllAddresses();
    int deleteAddress(int i);
}
