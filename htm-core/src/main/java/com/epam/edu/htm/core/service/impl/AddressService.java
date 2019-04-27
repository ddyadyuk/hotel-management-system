package com.epam.edu.htm.core.service.impl;

import com.epam.edu.htm.core.dao.AddressDao;
import com.epam.edu.htm.core.service.AddressOperations;
import com.epam.edu.htm.model.Address;

import java.util.List;

public class AddressService implements AddressOperations {
    private AddressDao addressDao;

    public AddressService(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    public Long addAddress(Address address) {

        if (address == null) {
            throw new IllegalArgumentException("Parameter 'address' can't be null");
        }

        return addressDao.addAddress(address).get();
    }

    @Override
    public Boolean editAddress(Address address) {
        if(address == null) {
            throw new IllegalArgumentException("Parameter 'address' can't e null");
        }

        return addressDao.editAddress(address);
    }

    @Override
    public List<Address> findAllAddresses() {
        return null;
    }

    @Override
    public Address findAddressById(Long id) {
        return null;
    }
}
