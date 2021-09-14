package com.softgris.cerberus.service;

import com.softgris.cerberus.dao.AddressDao;
import com.softgris.cerberus.pojo.AddressPojo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<AddressPojo> getAddress(Integer id) {
        return addressDao.getAddress(id);
    }

    @Override
    public List<AddressPojo> getAllAddresses() {
        return addressDao.getAllAddresses();
    }

    @Override
    public int deleteAddress(Integer id) {
        return addressDao.deleteAddress(id);
    }
}
