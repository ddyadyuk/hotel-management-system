package com.epam.edu.htm.model;

public class Contact {
    private Long idContact;
    private String phone;
    private String email;

    public Long getIdContact() {
        return idContact;
    }

    public void setIdContact(Long idContact) {
        this.idContact = idContact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

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
