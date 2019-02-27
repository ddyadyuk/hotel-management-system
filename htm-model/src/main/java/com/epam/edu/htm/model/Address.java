package com.epam.edu.htm.model;

public class Address {
    private Long addressId;
    private String firsAddress;
    private String secondAddress;
    private String thirdAddress;
    private String city;
    private String street;
    private String postalCode;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getFirsAddress() {
        return firsAddress;
    }

    public void setFirsAddress(String firsAddress) {
        this.firsAddress = firsAddress;
    }

    public String getSecondAddress() {
        return secondAddress;
    }
     public void setSecondAddress(String secondAddress) {
        this.secondAddress = secondAddress;
    }

    public String getThirdAddress() {
        return thirdAddress;
    }

    public void setThirdAddress(String thirdAddress) {
        this.thirdAddress = thirdAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", firsAddress='" + firsAddress + '\'' +
                ", secondAddress='" + secondAddress + '\'' +
                ", thirdAddress='" + thirdAddress + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
