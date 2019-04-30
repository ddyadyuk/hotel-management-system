package com.epam.edu.htm.dao;

import com.epam.edu.htm.core.dao.ContactDao;
import com.epam.edu.htm.dao.mapper.ContactMapper;
import com.epam.edu.htm.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class JdbcContactDao implements ContactDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(JdbcUserDao.class);

    private final static String CONTACT_ID = "contact_id";
    private final static String EMAIL = "email";
    private final static String PHONE = "phone";
    private final static String NO_ADDRESS_IN_DB_MESSAGE = "There are no contact with corresponding 'id' : {}";


    @Value("${add_contact}")
    private String addContactQuery;
    @Value("${edit_contact}")
    private String editContactQuery;
    @Value("${find_all_contacts}")
    private String findAllContactsQuery;
    @Value("${find_contact_by_id}")
    private String findContactByIdQuery;
    @Value("${delete_contact}")
    private String deleteContactQuery;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private ContactMapper contactMapper = new ContactMapper();

    @Autowired
    public JdbcContactDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<Long> addContact(Contact contact) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource
                .addValue(PHONE, contact.getPhone())
                .addValue(EMAIL, contact.getEmail());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowNumber = 0;
        try {
            rowNumber = namedParameterJdbcTemplate
                    .update(addContactQuery, mapSqlParameterSource, keyHolder, new String[]{CONTACT_ID});
            if (rowNumber == 0) {
                throw new EmptyResultDataAccessException(rowNumber);
            }
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Contact can't be added", e);
            throw e;
        }
        return Optional.of(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }

    @Override
    public Boolean editContact(Contact contact) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(CONTACT_ID, contact.getContactId())
                .addValue(PHONE, contact.getPhone())
                .addValue(EMAIL, contact.getEmail());

        int rowNum = 0;
        try {
            rowNum = namedParameterJdbcTemplate.update(editContactQuery, mapSqlParameterSource);
            if (rowNum == 0) {
                throw new EmptyResultDataAccessException(rowNum);
            }
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug(NO_ADDRESS_IN_DB_MESSAGE, contact.getContactId(), e);
            throw e;
        }

        return true;
    }

    @Override
    public List<Contact> findAllContacts() {
        return namedParameterJdbcTemplate.query(findAllContactsQuery, contactMapper);
    }

    @Override
    public Contact findContactById(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(CONTACT_ID, id);

        Contact result = new Contact();
        try {
            result = namedParameterJdbcTemplate
                    .queryForObject(findContactByIdQuery,mapSqlParameterSource,contactMapper);
        } catch (DataAccessException e) {
            LOGGER.debug(NO_ADDRESS_IN_DB_MESSAGE, id, e);
        }

        return result;
    }

    @Override
    public Boolean deleteContact(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(CONTACT_ID, id);

        int rowNumber = 0;
        try {
            rowNumber = namedParameterJdbcTemplate.update(deleteContactQuery, mapSqlParameterSource);
            if (rowNumber == 0) {
                throw new EmptyResultDataAccessException(rowNumber);
            }

        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug(NO_ADDRESS_IN_DB_MESSAGE,id ,e);
            throw e;
        }
        return true;
    }
}
