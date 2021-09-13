package com.softgris.cerberus.dao;

import com.softgris.cerberus.dto.AddressDto;
import com.softgris.cerberus.pojo.AddressPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface AddressDao {

    int saveAddress(BigInteger addressId, AddressDto address);

    Optional<AddressPojo> getAddress(BigInteger addressId);

    List<AddressPojo> getAllAddresses();

    int deleteAddress(BigInteger addressId);

    int updateAddress(BigInteger addressId, AddressDto address);
}
