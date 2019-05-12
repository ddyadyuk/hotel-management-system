package com.epam.edu.htm.client.dto;

import com.epam.edu.htm.model.Address;
import com.epam.edu.htm.model.Contact;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRestDto {

    private Long id;

    @NotNull
    private String password;

    @NotNull
    private String name;

    private Contact contact;

    private Address address;

    @NotNull
    private String userType;

    /**
     * Instantiates a new User rest dto.
     *
     * @param id       the id
     * @param password the password
     * @param name     the name
     * @param userType the user type
     */
    public UserRestDto(Long id, String password, String name, String userType) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.userType = userType;
    }

    /**
     * Instantiates a new User.
     *
     * @param password the password
     * @param contact  the contact
     * @param address  the address
     * @param userType the user type
     */
    public UserRestDto(String password, String name, Contact contact, Address address, String userType) {
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.userType = userType;
    }

    /**
     * Instantiates a new User rest dto.
     *
     * @param password the password
     * @param name     the name
     * @param userType the user type
     */
    public UserRestDto(String password, String name, String userType) {
        this.password = password;
        this.name = name;
        this.userType = userType;
    }

    /**
     * Instantiates a new User.
     */
    public UserRestDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "UserRestDto{"
                + "password='" + password + '\''
                + ", name='" + name + '\''
                + ", contact=" + contact
                + ", address=" + address
                + ", userType='" + userType + '\''
                + '}';
    }
}
