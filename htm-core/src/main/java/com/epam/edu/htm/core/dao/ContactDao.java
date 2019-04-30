package com.epam.edu.htm.core.dao;

import com.epam.edu.htm.model.Contact;

import java.util.List;
import java.util.Optional;

/**
 * The interface Contact dao.
 */
public interface ContactDao {
    /**
     * Add contact optional.
     *
     * @param contact the contact
     * @return the optional
     */
    Optional<Long> addContact(Contact contact);

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

    /**
     * Delete contact boolean.
     *
     * @param id the id
     * @return the boolean
     */
    Boolean deleteContact(Long id);
}
