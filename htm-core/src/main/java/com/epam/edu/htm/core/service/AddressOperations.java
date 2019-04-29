package com.epam.edu.htm.core.service;

import com.epam.edu.htm.model.Address;

import java.util.List;

/**
 * The interface Address operations.
 *
 * <P>various behavior methods of address </P>
 */
public interface AddressOperations {
    /**
     * Add address optional.
     *
     * @param address the address
     * @return the optional
     */
    Long addAddress(Address address);

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
