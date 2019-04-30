package com.epam.edu.htm.dao.dao;

import com.epam.edu.htm.core.dao.ContactDao;
import com.epam.edu.htm.dao.JdbcContactDao;
import com.epam.edu.htm.dao.mapper.ContactMapper;
import com.epam.edu.htm.model.Contact;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ContactDaoTest {
    @Mock
    private ContactDao contactDao;
    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @InjectMocks
    private JdbcContactDao jdbcContactDao;

    public ContactDaoTest() {
        MockitoAnnotations.initMocks(this);
    }


    @AfterEach
    public void after() {
        verifyNoMoreInteractions(contactDao);
        reset(contactDao);
    }

    @Test
    public void testAddContact_ContactIsOk_Success() {
        Contact contact = createTestContact();

        when(namedParameterJdbcTemplate.update(any(), any(MapSqlParameterSource.class), any(GeneratedKeyHolder.class),
                any(String[].class))).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Map<String, Object> keyMap = new HashMap<String, Object>();
                keyMap.put("", 1L);
                ((GeneratedKeyHolder) args[2]).getKeyList().add(keyMap);
                return 1;
            }
        });

        Optional<Long> result = jdbcContactDao.addContact(contact);

        assertNotNull(result);
        assertEquals(result, Optional.of(1L));

    }

    @Test
    public void testAddContact_ContactCantBeSaved_Fail() {
        Contact contact = createTestContact();

        when(namedParameterJdbcTemplate
                .update(any(), any(MapSqlParameterSource.class), any(KeyHolder.class), any(String[].class)))
                .thenThrow(EmptyResultDataAccessException.class);

        assertThrows(EmptyResultDataAccessException.class, () -> jdbcContactDao.addContact(contact));
    }

    @Test
    public void testEditContact_ContactIsOk_Success() {
        Contact contact = createTestContact();

        when(namedParameterJdbcTemplate.update(any(), any(MapSqlParameterSource.class))).thenReturn(1);

        Boolean result = jdbcContactDao.editContact(contact);

        assertNotNull(result);
        assertEquals(result, true);
    }

    @Test
    public void testEditContact_EmptyResult_Fail() {
        Contact contact = createTestContact();

        when(namedParameterJdbcTemplate.update(any(), any(MapSqlParameterSource.class)))
                .thenThrow(EmptyResultDataAccessException.class);

        assertThrows(EmptyResultDataAccessException.class, () -> jdbcContactDao.editContact(contact));
    }

    @Test
    public void testFindAllContacts_AllContactsIsPresent_Success() {
        List<Contact> result = jdbcContactDao.findAllContacts();

        assertNotNull(result);
    }

    @Test
    public void testFindContactById_IdIsCorrect_Success() {
        Contact contact = createTestContact();
        when(namedParameterJdbcTemplate.queryForObject(any(), any(MapSqlParameterSource.class), any(ContactMapper.class)))
                .thenReturn(contact);

        Contact result = jdbcContactDao.findContactById(1L);

        assertNotNull(result);
        assertEquals(result.getContactId(), (Long) 1L);
    }

    @Test
    public void testDeleteAddress_IdsOk_Success() {
        when(namedParameterJdbcTemplate.update(any(), any(MapSqlParameterSource.class))).thenReturn(1);

        Boolean result = jdbcContactDao.deleteContact(1L);

        assertNotNull(result);
        assertEquals(result, true);
    }

    @Test
    public void testDeleteAddress_EmptyResult_Fail() {
        when(namedParameterJdbcTemplate.update(any(), any(MapSqlParameterSource.class)))
                .thenThrow(EmptyResultDataAccessException.class);

        assertThrows(EmptyResultDataAccessException.class, () -> jdbcContactDao.deleteContact(1L));
    }


    private Contact createTestContact() {
        Contact contact = new Contact();
        contact.setContactId(1L);
        contact.setPhone("12345");
        contact.setEmail("ddyadyuk@epam.com");

        return contact;
    }
}
