package com.epam.edu.htm.model;

public class Room {
    private Long idRoom;
    private String status;
    private Double prisePerHour;
    private Long hotel;
    private Integer roomCapacity;
    private String roomType;

    public Long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Long idRoom) {
        this.idRoom = idRoom;
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
                "idRoom=" + idRoom +
                ", status='" + status + '\'' +
                ", prisePerHour=" + prisePerHour +
                ", hotel=" + hotel +
                ", roomCapacity=" + roomCapacity +
                ", roomType='" + roomType + '\'' +
                '}';
    }
}
