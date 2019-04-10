package com.epam.edu.htm.model;

/**
 * Room Model Object.
 *
 * <P>Various room attributes</P>
 *
 * @author Dmitry Dyadyuk
 * @version 1.0
 */
public class Room {
    private Long roomId;

    private String status;

    private Double prisePerHour;

    private Long hotel;

    private Integer roomCapacity;

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
