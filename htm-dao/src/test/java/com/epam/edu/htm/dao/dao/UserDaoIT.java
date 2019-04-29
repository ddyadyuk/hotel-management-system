package com.epam.edu.htm.dao.dao;

import com.epam.edu.htm.core.dao.UserDao;
import com.epam.edu.htm.dao.config.DataBaseTestConfig;
import com.epam.edu.htm.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DataBaseTestConfig.class)
@Transactional
@DirtiesContext
public class UserDaoIT {
//    private static final Contact USER_CONTACT = new Contact( 1L,
//            "123", "ddyadyuk@gmail.com" );
//    private static final Address USER_ADDRESS = new Address( 1L,
//            "634 Applegate Drive Hollis, NY 11423",
//            "New York", "St. Emporia", "122333412");

    @Autowired
    private UserDao userDao;

    @Test
    public void testAddUser_NotNull_Success() {
        User user = createTestUser();
        user.setUserId(null);

        Optional<Long> result = userDao.addUser(user);

        assertEquals(Optional.of(5L), result);
    }

    @Test
    public void testAddUser_IsNull_Fail() {
        assertThrows(IllegalArgumentException.class, () -> userDao.addUser(null));
    }

    @Test
    public void testDeleteUser_IsNotNull_Success() {
        User user = createTestUser();

        Boolean result = userDao.deleteUser(user.getUserId());

        assertEquals(result, true);
    }

    @Test
    public void testDeleteUser_IsNull_Fail() {
        assertThrows(IllegalArgumentException.class, () -> userDao.deleteUser(null));
    }

    @Test
    public void testFindAllUsers_AllUsersPresent_Success() {
        List<User> result = userDao.findAllUsers();

        assertNotNull(result);
        assertEquals(4, userDao.findAllUsers().size());
    }

    @Test
    public void testFindUserById_UserIdIsCorrect_Success() {
        User result = userDao.findUserById(1L);

        assertNotNull(result);
        assertEquals(result.getPassword(),"password");
    }

    @Test
    public void testFindUserById_UserIdIsNull_Fail() {
        assertThrows(IllegalArgumentException.class,() -> userDao.findUserById(null));
    }

    @Test
    public void testFindUserById_UserIsAbsentInDB_Fail() {
        assertThrows(EmptyResultDataAccessException.class, () -> userDao.findUserById(10L));
    }
    @Test
    public void testUpdateUser_UserIsCorrect_Success() {
        User user = createTestUser();

        Boolean result = userDao.editUser(user);

        assertNotNull(result);
        assertEquals(result, true);
    }

    @Test
    public void testUpdateUser_UserIsNull_Fail() {
        assertThrows(IllegalArgumentException.class, () -> userDao.editUser(null));
    }

    @Test
    public void testUpdateUser_UserIdIsNotFound_Fail() {
        User user = createTestUser();
        user.setUserId(10L);

        assertEquals(false, userDao.editUser(user));
    }

    private User createTestUser() {
        return new User(1L, "123","Yuri", "user");
    }
}
