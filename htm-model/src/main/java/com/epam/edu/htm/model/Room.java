package com.epam.edu.htm.model;

import javax.validation.constraints.NotNull;

/**
 * Room Model Object.
 *
 * <P>Various room attributes</P>
 *
 * @author Dmitry Dyadyuk
 * @version 1.0
 */
public class Room {
    @NotNull(message = "Room type cannot be null")
    private Long roomId;

    @NotNull(message = "Status cannot be null")
    private String status;

    @NotNull(message = "Price per hour cannot be null")
    private Double prisePerHour;

    @NotNull(message = "Hotel cannot be null")
    private Long hotel;

    @NotNull(message = "The room capacity cannot be null")
    private Integer roomCapacity;

    @NotNull(message = "The room type cannot be null")
    private String roomType;

    /**
     * Gets room id.
     *
     * @return the room id
     */
    public Long getRoomId() {
        return roomId;
    }

    /**
     * Sets room id.
     *
     * @param roomId the room id
     */
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    /**
     * Gets room status.
     * @return the room status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets room status.
     * @param status the room status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets price per hour.
     *
     * @return the price per hour
     */
    public Double getPrisePerHour() {
        return prisePerHour;
    }

    /**
     * Sets price per hour.
     *
     * @param prisePerHour the price per hour
     */
    public void setPrisePerHour(Double prisePerHour) {
        this.prisePerHour = prisePerHour;
    }

    /**
     * Gets hotel.
     *
     * @return the hotel
     */
    public Long getHotel() {
        return hotel;
    }

    /**
     * Sets hotel.
     *
     * @param hotel the hotel
     */
    public void setHotel(Long hotel) {
        this.hotel = hotel;
    }

    /**
     * Gets room capacity.
     *
     * @return the room capacity
     */
    public Integer getRoomCapacity() {
        return roomCapacity;
    }

    /**
     * Sets room capacity.
     *
     * @param roomCapacity the room capacity
     */
    public void setRoomCapacity(Integer roomCapacity) {
        this.roomCapacity = roomCapacity;
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

    @Override
    public String toString() {
        return "Room{"
                + "roomId=" + roomId
                + ", status='" + status + '\''
                + ", prisePerHour=" + prisePerHour
                + ", hotel=" + hotel
                + ", roomCapacity=" + roomCapacity
                + ", roomType='" + roomType + '\''
                + '}';
    }
}
