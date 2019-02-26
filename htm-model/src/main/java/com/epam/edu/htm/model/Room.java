package com.epam.edu.htm.model;

public class Room {
    private Long roomId;
    private String status;
    private Double prisePerHour;
    private Long hotel;
    private Integer roomCapacity;
    private String roomType;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrisePerHour() {
        return prisePerHour;
    }

    public void setPrisePerHour(Double prisePerHour) {
        this.prisePerHour = prisePerHour;
    }

    public Long getHotel() {
        return hotel;
    }

    public void setHotel(Long hotel) {
        this.hotel = hotel;
    }

    public Integer getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(Integer roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", status='" + status + '\'' +
                ", prisePerHour=" + prisePerHour +
                ", hotel=" + hotel +
                ", roomCapacity=" + roomCapacity +
                ", roomType='" + roomType + '\'' +
                '}';
    }
}
