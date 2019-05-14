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

        return addressDao.addAddress(address)
                .orElseThrow(() -> new IllegalArgumentException("The value is not present"));
    }

    @Override
    public Boolean editAddress(Address address) {
        if (address == null ) {
            throw new IllegalArgumentException("Parameter 'address' can't be null");
        }

        return addressDao.editAddress(address);
    }

    @Override
    public List<Address> findAllAddresses() {
        return addressDao.findAllAddresses();
    }

    @Override
    public Address findAddressById(Long id) {
        if (id == null ) {
            throw new IllegalArgumentException("Parameter 'id' can't be null");
        }

            return addressDao.findAddressById(id);
    }

    @Override
    public Boolean deleteAddress(Long id) {
        if (id == null ) {
            throw new IllegalArgumentException("Parameter 'id' can't be null");
        }
        return addressDao.deleteAddress(id);
    }
}
