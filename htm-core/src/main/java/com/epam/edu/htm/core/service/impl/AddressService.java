package com.epam.edu.htm.core.service.impl;

import com.epam.edu.htm.core.dao.AddressDao;
import com.epam.edu.htm.core.service.AddressOperations;
import com.epam.edu.htm.model.Address;

import java.util.List;

public class AddressService implements AddressOperations {
    private AddressDao addressDao;
    private static final String ADDRESS_MESSAGE = "Parameter 'address' can't be null";
    private static final String ID_NOT_NULL_MESSAGE = "Parameter 'id' can't be null";

    public AddressService(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    public Long addAddress(Address address) {

        if (address == null) {
            throw new IllegalArgumentException(ADDRESS_MESSAGE);
        }

        return addressDao.addAddress(address).get();
    }

    @Override
    public Boolean editAddress(Address address) {
        if (address == null || address.getAddressId() == null || address.getAddressId() <0) {
            throw new IllegalArgumentException(ADDRESS_MESSAGE);
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
            throw new IllegalArgumentException(ID_NOT_NULL_MESSAGE);
        }

            return addressDao.findAddressById(id);
    }

    @Override
    public Boolean deleteAddress(Long id) {
        if (id == null ) {
            throw new IllegalArgumentException(ID_NOT_NULL_MESSAGE);
        }
        return addressDao.deleteAddress(id);
    }
}
