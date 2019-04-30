package com.epam.edu.htm.core;

import com.epam.edu.htm.core.dao.ContactDao;
import com.epam.edu.htm.core.service.impl.ContactService;
import com.epam.edu.htm.model.Contact;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class ContactServiceTest {
    @Mock
    private ContactDao contactDao;
    @InjectMocks
    private ContactService contactService;

    public ContactServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void after() {
        verifyNoMoreInteractions(contactDao);
        Mockito.reset(contactDao);
    }

    @Test
    public void testAddContact_ContactIsOk_Success() {
        Contact contact = createTestContact();

        when(contactDao.addContact(contact)).thenReturn(Optional.of(1L));
        Long result = contactService.addContact(contact);

        assertNotNull(result);
        assertEquals(result,(Long) 1L);
    }

    @Test
    public void testAddContact_ContactIsNull_Fail() {
        when(contactDao.addContact(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> contactService.addContact(null));
    }

    @Test
    public void testDeleteContact_IdIsCorrect_Success() {
        when(contactDao.deleteContact(1L)).thenReturn(true);
        Boolean result = contactService.deleteContact(1L);

        assertNotNull(result);
        assertEquals(result, true);
    }

    @Test
    public void testDeleteContact_IdIsNul_Fail() {
        when(contactDao.deleteContact(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> contactService.deleteContact(null));
    }

    @Test
    public void testEditContact_ContactIsOk_Success() {
        Contact contact = createTestContact();

        when(contactDao.editContact(contact)).thenReturn(true);
        Boolean result = contactService.editContact(contact);

        assertNotNull(result);
        assertEquals(result, true);
    }

    @Test
    public void testEditContact_ContactIsNull_Fail() {
        when(contactDao.editContact(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> contactService.editContact(null));
    }

    @Test
    public void testFindAllContacts_AllContactsIsPresent_Success() {
        List<Contact> result = contactService.findAllContacts();

        assertNotNull(result);
    }

    @Test
    public void testFindContactById_IdIsOk_Success() {
        Contact contact = createTestContact();

        when(contactDao.findContactById(1L)).thenReturn(contact);
        Contact result = contactService.findContactById(1L);

        assertNotNull(result);
        assertEquals(result.getContactId(),(Long) 1L);
    }

    @Test
    public void testFindContactById_IdIsNull_Fail() {
        when(contactDao.findContactById(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> contactService.findContactById(null));
    }

    private Contact createTestContact() {
        Contact contact = new Contact();
        contact.setContactId(1L);
        contact.setPhone("12345");
        contact.setEmail("ddyadyuk@epam.com");

        return contact;
    }
}
