package com.epam.edu.htm.dao.dao;

import com.epam.edu.htm.core.dao.UserDao;
import com.epam.edu.htm.dao.config.DataBaseTestConfig;
import com.epam.edu.htm.model.Address;
import com.epam.edu.htm.model.Contact;
import com.epam.edu.htm.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DataBaseTestConfig.class)
@Transactional
public class UserDaoIT {
    private static final Long USER_ID = 1L;
    private static final String USER_PASSWORD = "123";
    private static final Long CONTACT_ID = 1L;
    private static final String PHONE = "123";
    private static final String EMAIL = "ddyadyuk@gmail.com";
    private static final Long ADDRESS_ID = 1L;
    private static final String FIRST_ADDRESS = "634 Applegate Drive Hollis, NY 11423";
    private static final String SECOND_ADDRESS = "7837 Cottage St. Emporia, KS 66801";
    private static final String THIRD_ADDRESS = "1 College Drive Lorain, OH 44052";
    private static final String CITY = "New York";
    private static final String STREET = "St. Emporia";
    private static final String POSTAL_CODE = "122333412";
    private static final String USER_TYPE = "user";
    private static final Contact USER_CONTACT = new Contact(CONTACT_ID, PHONE, EMAIL);
    private static final Address USER_ADDRESS = new Address(ADDRESS_ID,FIRST_ADDRESS, CITY,STREET ,POSTAL_CODE );

    @Autowired
    private UserDao userDao;

    @Test
    public void addUser_NotNull_success() {
        User user = createTestUser();

        Optional<Long> result = userDao.addUser(user);

        assertEquals(Optional.of(USER_ID), result);
    }

    @Test
    public void addUser_isNull_fail() {
        User user = null;

        assertThrows(IllegalArgumentException.class, () -> userDao.addUser(user));
    }

    private User createTestUser(){
        return new User(USER_ID,USER_PASSWORD, USER_CONTACT,USER_ADDRESS,USER_TYPE );
    }
}
