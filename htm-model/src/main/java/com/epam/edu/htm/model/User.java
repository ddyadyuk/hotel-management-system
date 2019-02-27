package com.epam.edu.htm.model;

/**
 * User Model Object.
 *
 * <P>Various user attributes</P>
 *
 * @author Dmitry Dyadyuk
 * @version 1.0
 */
public class User {
    private Long userId;
    private String password;
    private Contact contact;
    private Address address;
    private String userTypes;

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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
    public String getUserTypes() {
        return userTypes;
    }

    /**
     * Sets user type.
     *
     * @param userTypes the user type
     */
    public void setUserTypes(String userTypes) {
        this.userTypes = userTypes;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", contact=" + contact +
                ", address=" + address +
                ", userTypes=" + userTypes +
                '}';
    }


}
