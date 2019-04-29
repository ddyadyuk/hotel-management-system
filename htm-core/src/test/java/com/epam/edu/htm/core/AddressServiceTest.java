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

import java.util.List;
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
        Address address = createTestAddress();
        address.setAddressId(null);

        when(addressDao.editAddress(address)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> addressService.editAddress(address));
    }

    @Test
    public void testEditAddress_AddressIdIsLessThanZero_Fail() {
        Address address = createTestAddress();
        address.setAddressId(-2L);

        when(addressDao.editAddress(address)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> addressService.editAddress(address));
    }


    @Test
    public void testEditAddress_AddressIsNull_Fail() {
        when(addressDao.editAddress(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> addressService.editAddress(null));
    }


    @Test
    public void testFindAllAddresses_AllAddressesIsPresent_Success() {
        List<Address> result = addressService.findAllAddresses();

        assertNotNull(result);
    }

    @Test
    public void testFindAddressById_IdIsOk_Success() {
        Address address = createTestAddress();

        when(addressDao.findAddressById(1L)).thenReturn(address);
        Address result = addressService.findAddressById(1L);

        assertNotNull(result);
    }

    @Test
    public void testFindAddressById_IdIsNull_Fail() {
        when(addressDao.findAddressById(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,() -> addressService.findAddressById(null));
    }

    @Test
    public void testDeleteAddres_IdISCorrect_Success() {
        when(addressDao.deleteAddress(1L)).thenReturn(true);

        Boolean result = addressService.deleteAddress(1L);

        assertNotNull(result);
        assertEquals(result, true);
    }

    @Test
    public void testDeleteAddress_IdIsNull_Fail() {
        when(addressDao.deleteAddress(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> addressService.deleteAddress(null));
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
