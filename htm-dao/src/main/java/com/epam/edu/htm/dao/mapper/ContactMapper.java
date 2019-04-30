package com.epam.edu.htm.dao.mapper;

import com.epam.edu.htm.model.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactMapper implements RowMapper<Contact> {

    private static final String PHONE = "phone";
    private static final String EMAIL = "email";

    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setEmail(EMAIL);
        contact.setPhone(PHONE);

        return null;
    }
}
