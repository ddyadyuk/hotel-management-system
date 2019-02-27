package com.epam.edu.htm.model;

public class Guest {
    private Long idGuest;
    private Long idUser;

    public Long getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(Long idGuest) {
        this.idGuest = idGuest;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "idGuest=" + idGuest +
                ", idUser=" + idUser +
                '}';
    }
}




