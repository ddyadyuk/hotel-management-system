package com.epam.edu.htm.model;

import java.sql.Date;

public class Reservation {
    private Long reservationId;
    private String country;
    private String roomType;
    private Date startBooking;
    private Date endBooking;
    private Room roomId;
    private Long guestId;
    private Long hotelId;

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Date getStartBooking() {
        return startBooking;
    }

    public void setStartBooking(Date startBooking) {
        this.startBooking = startBooking;
    }

    public Date getEndBooking() {
        return endBooking;
    }

    public void setEndBooking(Date endBooking) {
        this.endBooking = endBooking;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", country='" + country + '\'' +
                ", roomType='" + roomType + '\'' +
                ", startBooking=" + startBooking +
                ", endBooking=" + endBooking +
                ", roomId=" + roomId +
                ", guestId=" + guestId +
                ", hotelId=" + hotelId +
                '}';
    }
}
