package com.epam.edu.htm.core.dao;

import com.epam.edu.htm.model.Address;

import java.util.List;
import java.util.Optional;

/**
 * The interface Address dao.
 */
public interface AddressDao {

    /**
     * Add address optional.
     *
     * @param address the address
     * @return the optional
     */
    Optional<Long> addAddress(Address address);


    /**
     * Edit address boolean.
     *
     * @param address the address
     * @return the boolean
     */
    Boolean editAddress(Address address);

    /**
     * Find all addresses list.
     *
     * @return the list
     */
    List<Address> findAllAddresses();

    /**
     * Find address by id address.
     *
     * @param id the id
     * @return the address
     */
    Address findAddressById(Long id);

    /**
     * Delete address boolean.
     *
     * @param id the id
     * @return the boolean
     */
    Boolean deleteAddress(Long id);
}
