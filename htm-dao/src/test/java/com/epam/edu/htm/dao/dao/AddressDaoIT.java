package com.epam.edu.htm.dao.dao;

import com.epam.edu.htm.core.dao.AddressDao;
import com.epam.edu.htm.dao.config.DataBaseTestConfig;
import com.epam.edu.htm.model.Address;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DataBaseTestConfig.class)
@Transactional
public class AddressDaoIT {

    @Autowired
    private AddressDao addressDao;

    @Test
    public void testAddAddress_NotNull_Success() {
        Address address = createTestAddress();

        Optional<Long> result = addressDao.addAddress(address);

        assertEquals(Optional.of(4L), result);
    }

    @Test
    public void testAddAddress_AddressIsNull_Fail() {
        assertThrows(IllegalArgumentException.class, () -> addressDao.addAddress(null));
    }

    @Test
    public void testEditAddress_IdIsCorresponding_Success() {
        Address address = createTestAddress();
        Boolean result = addressDao.editAddress(address);

        assertNotNull(result);
        assertEquals(result, true);
    }

    @Test
    public void testEditAddress_IdIsNull_Fail() {
        assertThrows(IllegalArgumentException.class, () -> addressDao.editAddress(null));
    }

    private Address createTestAddress() {
        return new Address(1L, "Europe", "Belarus", "Brest region", "Brest", "Masherova", "12345");
    }
}
