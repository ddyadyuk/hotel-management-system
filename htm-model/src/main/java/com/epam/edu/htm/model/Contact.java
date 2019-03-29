package com.epam.edu.htm.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Contact Model Object.
 *
 * <P>Various contact attributes</P>
 *
 * @author Dmitry Dyadyuk
 * @version 1.0
 */
public class Contact {
    @NotNull
    private Long contactId;

    @Size(min = 7, max = 15, message = "phone number cannot be lover that 7 and greater than 15")
    private String phone;

    @Email(message = "Email should be valid")
    private String email;

    /**
     * Instantiates a new Contact.
     *
     * @param idContact the id contact
     * @param phone     the phone
     * @param email     the email
     */
    public Contact(Long idContact, String phone, String email) {
        this.contactId = idContact;
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
    public Long getContactId() {
        return contactId;
    }

    /**
     * Sets contact id.
     *
     * @param contactId the contact id
     */
    public void setContactId(Long contactId) {
        this.contactId = contactId;
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
        return "Contact{"
                + "contactId=" + contactId
                + ", phone='" + phone + '\''
                + ", email='" + email + '\''
                + '}';
    }
}
