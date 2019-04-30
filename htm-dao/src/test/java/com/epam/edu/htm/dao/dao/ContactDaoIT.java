package com.epam.edu.htm.dao.dao;

import com.epam.edu.htm.core.dao.ContactDao;
import com.epam.edu.htm.dao.config.DataBaseTestConfig;
import com.epam.edu.htm.model.Contact;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DataBaseTestConfig.class)
@Transactional
@DirtiesContext
public class ContactDaoIT {

    @Autowired
    private ContactDao contactDao;

    @Test
    public void testAddContact_contactIsOk_Success() {
        Contact contact = createTestContact();

        Optional<Long> result = contactDao.addContact(contact);

        assertNotNull(result);
    }

    private Contact createTestContact() {
        Contact contact = new Contact();
        contact.setContactId(1L);
        contact.setPhone("12345");
        contact.setEmail("ddyadyuk@epam.com");

        return contact;
    }
}
