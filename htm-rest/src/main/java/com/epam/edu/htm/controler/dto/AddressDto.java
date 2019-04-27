package com.epam.edu.htm.controler.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The type Address dto.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto {

    private String firsAddress;

    private String secondAddress;

    private String thirdAddress;

    private String city;

    private String street;

    private String postalCode;

    /**
     * Instantiates a new Address dto.
     */
    public AddressDto() {
    }

    /**
     * Instantiates a new Address dto.
     *
     * @param firsAddress   the firs address
     * @param secondAddress the second address
     * @param thirdAddress  the third address
     * @param city          the city
     * @param street        the street
     * @param postalCode    the postal code
     */
    public AddressDto(String firsAddress, String secondAddress, String thirdAddress,
                      String city, String street, String postalCode) {
        this.firsAddress = firsAddress;
        this.secondAddress = secondAddress;
        this.thirdAddress = thirdAddress;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }

    /**
     * Gets firs address.
     *
     * @return the firs address
     */
    public String getFirsAddress() {
        return firsAddress;
    }

    /**
     * Sets firs address.
     *
     * @param firsAddress the firs address
     */
    public void setFirsAddress(String firsAddress) {
        this.firsAddress = firsAddress;
    }

    /**
     * Gets second address.
     *
     * @return the second address
     */
    public String getSecondAddress() {
        return secondAddress;
    }

    /**
     * Sets second address.
     *
     * @param secondAddress the second address
     */
    public void setSecondAddress(String secondAddress) {
        this.secondAddress = secondAddress;
    }

    /**
     * Gets third address.
     *
     * @return the third address
     */
    public String getThirdAddress() {
        return thirdAddress;
    }

    /**
     * Sets third address.
     *
     * @param thirdAddress the third address
     */
    public void setThirdAddress(String thirdAddress) {
        this.thirdAddress = thirdAddress;
    }

    /**
     * Gets city.
     *
     * @return the city
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
        return "AddressDto{" +
                "firsAddress='" + firsAddress
                + '\'' + ", secondAddress='" + secondAddress
                + '\'' + ", thirdAddress='" + thirdAddress
                + '\'' + ", city='" + city
                + '\'' + ", street='" + street
                + '\'' + ", postalCode='" + postalCode
                + '\'' + '}';
    }
}
