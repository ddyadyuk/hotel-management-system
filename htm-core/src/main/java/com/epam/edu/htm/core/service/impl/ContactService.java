package com.epam.edu.htm.core.service.impl;

import com.epam.edu.htm.core.dao.ContactDao;
import com.epam.edu.htm.core.service.ContactOperations;
import com.epam.edu.htm.model.Contact;

import java.util.List;

public class ContactService implements ContactOperations {

    private static final String CONTACT_NOT_NULL_MESSAGE = "Parameter 'contact' can not be null";
    private static final String ID_NOT_NULL_MESSAGE = "Parameter 'id' can not be null";
    private ContactDao contactDao;

    public ContactService(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public Long addContact(Contact contact) {
        if(contact == null) {
            throw new IllegalArgumentException(CONTACT_NOT_NULL_MESSAGE);
        }
        if(!contactDao.addContact(contact).isPresent()){
            throw new IllegalArgumentException("Method can't add this contact");
        }

        return contactDao.addContact(contact).get();
    }

    @Override
    public Boolean deleteContact(Long id) {
        if(id == null) {
            throw new IllegalArgumentException();
        }

        return contactDao.deleteContact(id);
    }

    @Override
    public Boolean editContact(Contact contact) {
        if(contact == null) {
            throw new IllegalArgumentException(CONTACT_NOT_NULL_MESSAGE);
        }

        return contactDao.editContact(contact);
    }

    @Override
    public List<Contact> findAllContacts() {
        return contactDao.findAllContacts();
    }

    @Override
    public Contact findContactById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException(ID_NOT_NULL_MESSAGE);
        }

        return contactDao.findContactById(id);
    }
}
