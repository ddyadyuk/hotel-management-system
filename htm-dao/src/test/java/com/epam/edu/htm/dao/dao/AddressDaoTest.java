package com.epam.edu.htm.dao.dao;

import com.epam.edu.htm.core.dao.AddressDao;
import com.epam.edu.htm.dao.JdbcAddressDao;
import com.epam.edu.htm.model.Address;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.platform.commons.logging.LoggerFactory;
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
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AddressDaoTest {
    @Mock
    private AddressDao addressDao;
    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @InjectMocks
    private JdbcAddressDao jdbcAddressDao;

    public AddressDaoTest() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void after() {
        verifyNoMoreInteractions(addressDao);
        reset(addressDao);
    }

    @Test
    public void testAddAddress_addressIsOk_Success() {
        Address address = createTestAddress();

        when(namedParameterJdbcTemplate.update(any(), any(MapSqlParameterSource.class), any(KeyHolder.class), any(String[].class)))
                .thenAnswer(new Answer() {
                    @Override
                    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                        Object[] args = invocationOnMock.getArguments();
                        Map<String, Object> keyMap = new HashMap<String, Object>();
                        keyMap.put("", 1);
                        ((GeneratedKeyHolder) args[2]).getKeyList().add(keyMap);
                        return 1;
                    }
                });

        Optional<Long> result = jdbcAddressDao.addAddress(address);

        assertNotNull(result);
        assertEquals(result, Optional.of(1L));

    }

    @Test
    public void testAddAddress_AddressIsNull_Fail() {

        when(namedParameterJdbcTemplate.update(any(), any(MapSqlParameterSource.class),
                any(KeyHolder.class), any(String[].class))).thenReturn(0);

        assertThrows(IllegalArgumentException.class, () -> jdbcAddressDao.addAddress(null));
    }

    @Test
    public void testAddAddress_AddressNotAdded_EmptyResultEx() {
        Address address = createTestAddress();

        when(namedParameterJdbcTemplate.update(any(), any(MapSqlParameterSource.class),
                any(KeyHolder.class), any(String[].class))).thenReturn(0);

        assertThrows(EmptyResultDataAccessException.class, () -> jdbcAddressDao.addAddress(address));
    }

    @Test
    public void testEditAddress_CorrectIdISPresent_Success() {
        Address address = createTestAddress();

        when(namedParameterJdbcTemplate.update(any(),any(MapSqlParameterSource.class))).thenReturn(1);

        Boolean result = jdbcAddressDao.editAddress(address);

        assertNotNull(result);
        assertEquals(result, true);
    }

    @Test
    public void testEditAddress_IdIsNull_Fail() {

        when(namedParameterJdbcTemplate.update(any(),any(MapSqlParameterSource.class))).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> jdbcAddressDao.editAddress(null));
    }

    @Test
    public void testEditAddress_UpdateResultIsZero_EmptyResult() {
        Address address = createTestAddress();

        when(namedParameterJdbcTemplate.update(any(), any(MapSqlParameterSource.class))).thenThrow(EmptyResultDataAccessException.class);

        assertThrows(EmptyResultDataAccessException.class, () -> jdbcAddressDao.editAddress(address));
    }

    private Address createTestAddress() {
        return new Address(1L, "Europe", "Belarus", "Brest region", "Brest", "Masherova", "12345");
    }
}
