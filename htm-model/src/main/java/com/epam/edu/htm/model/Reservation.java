package com.epam.edu.htm.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Hotel Model Object.
 *
 * <P>Various hotel attributes</P>
 *
 * @author Dmitry Dyadyuk
 * @version 1.0
 */
public class Reservation {
    private Long reservationId;

    private String country;

    private String roomType;

    private Date startBooking;

    private Date endBooking;

    private Room roomId;

    private Long guestId;

    private Long hotelId;

    /**
     * Gets reservation id.
     *
     * @return the reservation id
     */
    public Long getReservationId() {
        return reservationId;
    }

    /**
     * Sets reservation id.
     *
     * @param reservationId the reservation id
     */
    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country th country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets room type.
     *
     * @return the room type
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * Sets room type.
     *
     * @param roomType the room type
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    /**
     * Gets start of the booking.
     *
     * @return the start of the booking
     */
    public Date getStartBooking() {
        return startBooking;
    }

    /**
     * Sets start of the booking.
     *
     * @param startBooking the start of the booking
     */
    public void setStartBooking(Date startBooking) {
        this.startBooking = startBooking;
    }

    /**
     * Gets end of the booking.
     * @return the end of the booking
     */
    public Date getEndBooking() {
        return endBooking;
    }

    /**
     * Sets end of the booking.
     *
     * @param endBooking the end of the booking
     */
    public void setEndBooking(Date endBooking) {
        this.endBooking = endBooking;
    }

    /**
     * Gets room id.
     * @return the room id
     */
    public Room getRoomId() {
        return roomId;
    }

    /**
     * Sets room id.
     *
     * @param roomId the room id
     */
    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    /**
     * Gets guest id.
     *
     * @return the guest id
     */
    public Long getGuestId() {
        return guestId;
    }

    /**
     * Sets guest id.
     *
     * @param guestId the guest id
     */
    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    /**
     * Gets hotel id.
     *
     * @return the hotel id
     */
    public Long getHotelId() {
        return hotelId;
    }

    /**
     * Sets hotel id.
     * @param hotelId the hotel id
     */
    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return "Reservation{"
                + "reservationId=" + reservationId
                + ", country='" + country + '\''
                + ", roomType='" + roomType + '\''
                + ", startBooking=" + startBooking
                + ", endBooking=" + endBooking
                + ", roomId=" + roomId
                + ", guestId=" + guestId
                + ", hotelId=" + hotelId
                + '}';
    }
}
