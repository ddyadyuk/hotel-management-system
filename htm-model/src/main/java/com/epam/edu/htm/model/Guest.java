package com.epam.edu.htm.model;

/**
 * Guest Model Object.
 *
 * <P>Various guest attributes</P>
 *
 * @author Dmitry Dyadyuk
 * @version 1.0
 */
public class Guest {
    private Long idGuest;
    private Long idUser;

    /**
     * Gets guest id.
     *
     * @return the guest id
     */
    public Long getIdGuest() {
        return idGuest;
    }

    /**
     * Sets guest id.
     *
     * @param idGuest the guest id
     */
    public void setIdGuest(Long idGuest) {
        this.idGuest = idGuest;
    }


    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getIdUser() {
        return idUser;
    }

    /**
     * Sets user id.
     *
     * @param idUser the user id
     */
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




