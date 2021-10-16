package com.softgris.cerberus.service;

import com.softgris.cerberus.dao.user.AddressDao;
import com.softgris.cerberus.pojo.AddressPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    AddressDao addressDao;

    public AddressServiceImpl(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    public int saveAddress(AddressPojo address) {
        return addressDao.saveAddress(address);
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
    public int deleteAddress(BigInteger addressId) {
        return addressDao.deleteAddress(addressId);
    }
}
