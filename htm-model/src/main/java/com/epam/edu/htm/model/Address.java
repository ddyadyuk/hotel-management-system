package com.epam.edu.htm.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Address Model Object.
 *
 * <P>Various address attributes</P>
 *
 * @author Dmitry Dyadyuk
 * @version 1.0
 */
public class Address {
    private Long addressId;

    private String firsAddress;

    private String secondAddress;

    private String thirdAddress;

    private String city;

    private String street;

    private String postalCode;

    /**
     * Instantiates a new Address.
     *
     * @param addressId     the address id
     * @param firsAddress   the firs address
     * @param city          the city
     * @param street        the street
     * @param postalCode    the postal code
     */
    public Address(Long addressId, String firsAddress,
                    String city, String street, String postalCode) {
        this.addressId = addressId;
        this.firsAddress = firsAddress;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }

    /**
     * Instantiates a new Address.
     */
    public Address() {
    }

    /**
     * Gets AddressId.
     *
     * @return the user address
     */
    public Long getAddressId() {
        return addressId;
    }

    /**
     * Sets AddressId.
     *
     * @param addressId the address id.
     */
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    /**
     * Gets first user address.
     *
     * @return the first user address
     */
    public String getFirsAddress() {
        return firsAddress;
    }

    /**
     * Gets user first address.
     *
     * @param firsAddress the first user address
     */
    public void setFirsAddress(String firsAddress) {
        this.firsAddress = firsAddress;
    }

    /**
     * Gets second user address.
     *
     * @return the second user address
     */
    public String getSecondAddress() {
        return secondAddress;
    }

    /**
     * Sets second user address.
     *
     * @param secondAddress the second user address
     */
    public void setSecondAddress(String secondAddress) {
        this.secondAddress = secondAddress;
    }

    /**
     * Gets third user address.
     *
     * @return the thisd user address
     */
    public String getThirdAddress() {
        return thirdAddress;
    }

    /**
     * Sets third user address.
     *
     * @param thirdAddress the third user address
     */
    public void setThirdAddress(String thirdAddress) {
        this.thirdAddress = thirdAddress;
    }

    /**
     * Gets city.
     *
     * @return city city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets street.
     *
     * @param street the street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets postal code.
     *
     * @return the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets postal code.
     *
     * @param postalCode the postal code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Address{"
                + "addressId=" + addressId
                + ", firsAddress='" + firsAddress + '\''
                + ", secondAddress='" + secondAddress + '\''
                + ", thirdAddress='" + thirdAddress + '\''
                + ", city='" + city + '\''
                + ", street='" + street + '\''
                + ", postalCode='" + postalCode + '\''
                + '}';
    }
}
