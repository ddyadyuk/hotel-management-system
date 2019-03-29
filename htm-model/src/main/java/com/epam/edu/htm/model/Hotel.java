package com.epam.edu.htm.model;

import javax.validation.constraints.NotNull;

/**
 * Hotel Model Object.
 *
 * <P>Various hotel attributes</P>
 *
 * @author Dmitry Dyadyuk
 * @version 1.0
 */
public class Hotel {
    @NotNull
    private Long hotelId;

    @NotNull
    private Contact contact;

    @NotNull
    private String hotelType;

    @NotNull
    private Address address;

    /**
     * Getshotel id.
     *
     * @return the hotel id
     */
    public Long getHotelId() {
        return hotelId;
    }

    /**
     * Sets hotel id.
     *
     * @param hotelId the hotel id
     */
    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * Gets hotel contact.
     *
     * @return the hotel contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Sets hotel contact.
     * @param contact the hotel contact
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * Gets type of the hotel.
     *
     * @return the type of the hotel
     */
    public String getHotelType() {
        return hotelType;
    }

    /**
     * Sets type of the hotel.
     *
     * @param hotelType the type of the hotel
     */
    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    /**
     * Gets hotel address.
     *
     * @return the hotel address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets hotel address.
     * @param address the hotel address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Hotel{"
                + "hotelId=" + hotelId
                + ", contact=" + contact
                + ", hotelType='" + hotelType + '\''
                + ", address=" + address
                + '}';
    }
}
