package com.epam.edu.htm.model;

import java.util.List;

public class Hotel {
    private Long idHotel;
    private Contact contacts;
    private String hotelType;
    private Address addresses;

    public Long getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Long idHotel) {
        this.idHotel = idHotel;
    }

    public Contact getContacts() {
        return contacts;
    }

    public void setContacts(Contact contacts) {
        this.contacts = contacts;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    public Address getAddresses() {
        return addresses;
    }

    public void setAddresses(Address addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "idHotel=" + idHotel +
                ", contacts=" + contacts +
                ", hotelType='" + hotelType + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
