package com.epam.edu.htm.controler.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactDto {
    private String phone;

    private String email;

    public ContactDto(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }

    public ContactDto() {
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
        return "ContactDto{" +
                "phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}