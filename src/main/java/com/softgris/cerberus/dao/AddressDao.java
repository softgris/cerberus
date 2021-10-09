package com.softgris.cerberus.dao;

import com.softgris.cerberus.pojo.AddressPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface AddressDao {

    int saveAddress(AddressPojo address);

    Optional<AddressPojo> getAddress(BigInteger userId);

    List<AddressPojo> getAllAddresses();

    int deleteAddress(BigInteger userId);

    int updateAddress(AddressPojo address);
}
