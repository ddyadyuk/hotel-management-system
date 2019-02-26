package com.epam.edu.htm.model;

public class User {
    private Long userId;
    private String password;
    private Contact contact;
    private Address address;
    private String userTypes;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Contact getContacts() {
        return contact;
    }

    public void setContacts(Contact contacts) {
        this.contact = contacts;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(String userTypes) {
        this.userTypes = userTypes;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", contacts=" + contact +
                ", address=" + address +
                ", userTypes=" + userTypes +
                '}';
    }


}
