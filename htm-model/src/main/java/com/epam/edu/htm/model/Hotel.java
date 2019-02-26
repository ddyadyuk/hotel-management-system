package com.epam.edu.htm.model;

public class Hotel {
    private Long hotelId;
    private Contact contact;
    private String hotelType;
    private Address address;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Contact getContacts() {
        return contact;
    }

    public void setContacts(Contact contacts) {
        this.contact = contacts;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    public Address getAddresses() {
        return address;
    }

    public void setAddresses(Address addresses) {
        this.address = addresses;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", contacts=" + contact +
                ", hotelType='" + hotelType + '\'' +
                ", address=" + address +
                '}';
    }
}
