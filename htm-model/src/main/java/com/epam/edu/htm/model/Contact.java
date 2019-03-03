package com.epam.edu.htm.model;

/**
 * Contact Model Object.
 *
 * <P>Various contact attributes</P>
 *
 * @author Dmitry Dyadyuk
 * @version 1.0
 */
public class Contact {
    private Long idContact;
    private String phone;
    private String email;

    /**
     * Instantiates a new Contact.
     *
     * @param idContact the id contact
     * @param phone     the phone
     * @param email     the email
     */
    public Contact(Long idContact, String phone, String email) {
        this.idContact = idContact;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Instantiates a new Contact.
     */
    public Contact() {
    }

    /**
     * Gets contact id.
     *
     * @return the contact id
     */
    public Long getIdContact() {
        return idContact;
    }

    /**
     * Sets contact id.
     *
     * @param idContact the contact id
     */
    public void setIdContact(Long idContact) {
        this.idContact = idContact;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone number.
     *
     * @param phone the phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "idContact=" + idContact +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
