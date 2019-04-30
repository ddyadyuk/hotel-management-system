package com.epam.edu.htm.core.service;

import com.epam.edu.htm.model.Contact;

import java.util.List;

/**
 * The interface Contact operations.
 */
public interface ContactOperations {
    /**
     * Add contact long.
     *
     * @param contact the contact
     * @return the long
     */
    Long addContact(Contact contact);

    /**
     * Delete contact boolean.
     *
     * @param id the id
     * @return the boolean
     */
    Boolean deleteContact(Long id);

    /**
     * Edit contact boolean.
     *
     * @param contact the contact
     * @return the boolean
     */
    Boolean editContact(Contact contact);

    /**
     * Find all contacts list.
     *
     * @return the list
     */
    List<Contact> findAllContacts();

    /**
     * Find contact by id contact.
     *
     * @param id the id
     * @return the contact
     */
    Contact findContactById(Long id);
}
