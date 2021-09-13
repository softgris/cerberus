package com.softgris.cerberus.service;

import com.softgris.cerberus.dao.AddressDao;
import com.softgris.cerberus.dto.AddressDto;
import com.softgris.cerberus.pojo.AddressPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    AddressDao addressDao;

    public AddressServiceImpl(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    public BigInteger saveAddress(AddressDto address) {
        BigInteger addressId = BigInteger.valueOf(
            ThreadLocalRandom.current().nextLong(1_000_000_000L, 9_999_999_999L));
        return addressDao.saveAddress(addressId, address) > 0 ? addressId : null;
    }

    @Override
    public Optional<AddressPojo> getAddress(BigInteger addressId) {
        return addressDao.getAddress(addressId);
    }

    @Override
    public List<AddressPojo> getAllAddresses() {
        return addressDao.getAllAddresses();
    }

    @Override
    public boolean deleteAddress(BigInteger addressId) {
        return addressDao.deleteAddress(addressId) > 0;
    }

    @Override
    public boolean updateAddress(BigInteger addressId, AddressDto address) {
        return addressDao.updateAddress(addressId, address) > 0;
    }
}
