package com.epam.edu.htm.controler.dto;

import com.epam.edu.htm.model.Address;
import com.epam.edu.htm.model.Contact;

import javax.validation.constraints.NotNull;

public class UserDto {

    @NotNull
    private String password;

    private Contact contact;

    private Address address;

    @NotNull
    private String userType;

    /**
     * Instantiates a new User.
     *
     * @param password the password
     * @param contact  the contact
     * @param address  the address
     * @param userType the user type
     */
    public UserDto(String password, Contact contact, Address address, String userType) {
        this.password = password;
        this.contact = contact;
        this.address = address;
        this.userType = userType;
    }

    /**
     * Instantiates a new User.
     */
    public UserDto() {
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets contact.
     *
     * @return the contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Sets contact.
     *
     * @param contact the contact
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets user type.
     *
     * @return the user type
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets user type.
     *
     * @param userType the user type
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }
}