package com.softgris.cerberus.service;

import com.softgris.cerberus.dto.AddressDto;
import com.softgris.cerberus.pojo.AddressPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface AddressService {

    BigInteger saveAddress(AddressDto address);

    Optional<AddressPojo> getAddress(BigInteger i);

    List<AddressPojo> getAllAddresses();

    boolean deleteAddress(BigInteger i);

    boolean updateAddress(BigInteger i, AddressDto address);
}
