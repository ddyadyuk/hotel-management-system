package com.epam.edu.htm.core;

import com.epam.edu.htm.core.dao.AddressDao;
import com.epam.edu.htm.core.service.impl.AddressService;
import com.epam.edu.htm.model.Address;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AddressServiceTest {
    @Mock
    private AddressDao addressDao;
    @InjectMocks
    private AddressService addressService;

    public AddressServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void after() {
        verifyNoMoreInteractions(addressDao);
        Mockito.reset(addressDao);
    }

    @Test
    public void testAddAddress_AddressIsOk_Success() {
        Address address = createTestAddress();

        when(addressDao.addAddress(address)).thenReturn(Optional.of(1L));

        Long result = addressService.addAddress(address);

        assertNotNull(result);
        assertEquals(result, (Long) 1L);
        verify(addressDao, times(1)).addAddress(address);
    }

    @Test
    public void testAddAddress_AddressIsNull_Fail() {
        when(addressDao.addAddress(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> addressService.addAddress(null));
        verifyZeroInteractions(addressDao);
    }

    @Test
    public void testEditUser_AddressIdIsOk_Success() {
        Address address = createTestAddress();
        when(addressDao.editAddress(address)).thenReturn(true);

        Boolean result = addressService.editAddress(address);

        assertNotNull(result);
        assertEquals(result, true);
    }

    @Test
    public void testEditAddress_AddressIdIsNull_Fail() {
        when(addressDao.editAddress(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> addressService.editAddress(null));
    }

    private Address createTestAddress() {
        Address address = new Address();
        address.setAddressId(1L);
        address.setFirsAddress("Belarus");
        address.setSecondAddress("Brest region");
        address.setCity("Brest");
        address.setStreet("Masherova");
        address.setPostalCode("12345");

        return address;
    }
}
