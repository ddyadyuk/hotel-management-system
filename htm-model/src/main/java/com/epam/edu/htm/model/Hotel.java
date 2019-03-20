package com.epam.edu.htm.model;

/**
 * Hotel Model Object.
 *
 * <P>Various hotel attributes</P>
 *
 * @author Dmitry Dyadyuk
 * @version 1.0
 */
public class Hotel {
    private Long idHotel;
    private Contact contact;
    private String hotelType;
    private Address address;

    /**
     * Getshotel id.
     *
     * @return the hotel id
     */
    public Long getIdHotel() {
        return idHotel;
    }

    /**
     * Sets hotel id.
     *
     * @param idHotel the hotel id
     */
    public void setIdHotel(Long idHotel) {
        this.idHotel = idHotel;
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
                + "idHotel=" + idHotel
                + ", contact=" + contact
                + ", hotelType='" + hotelType + '\''
                + ", address=" + address
                + '}';
    }
}
