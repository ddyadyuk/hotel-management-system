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
    private static final Contact USER_CONTACT = new Contact( 1L,
            "123", "ddyadyuk@gmail.com" );
    private static final Address USER_ADDRESS = new Address( 1L,
            "634 Applegate Drive Hollis, NY 11423",
            "New York", "St. Emporia", "122333412");

    @Autowired
    private UserDao userDao;

    @Test
    public void addUser_NotNull_success() {
        User user = createTestUser();

        Long result = userDao.addUser(user);

        assertEquals((Long) 1L, result);
    }

    @Test
    public void addUser_isNull_fail() {
        assertThrows(IllegalArgumentException.class, () -> userDao.addUser(null));
    }

    private User createTestUser(){
        return new User(1L,"123", USER_CONTACT, USER_ADDRESS, "user");
    }
}
