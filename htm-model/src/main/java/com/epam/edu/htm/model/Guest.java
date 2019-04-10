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
    private Long guestId;

    private Long userId;

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
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Guest{"
                + "guestId=" + guestId
                + ", userId=" + userId
                + '}';
    }
}




