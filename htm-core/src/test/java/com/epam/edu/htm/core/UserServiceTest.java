package com.epam.edu.htm.core;

import com.epam.edu.htm.core.dao.UserDao;
import com.epam.edu.htm.core.service.impl.UserService;
import com.epam.edu.htm.model.User;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class    UserServiceTest {
    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void after() {
        verifyNoMoreInteractions(userDao);
        Mockito.reset(userDao);
    }

    @Test
    public void testAddUser_UserNotNull_Success() {

        User user = createTestUser();

        Mockito.when(userDao.addUser(user)).thenReturn(Optional.of(1L));
        Long result = userService.addUser(user);

        Mockito.verify(userDao, Mockito.times(1)).addUser(user);
    }

    @Test
    public void testAddUser_UserNotNull_ResponseNotnull() {

        User user = createTestUser();

        Mockito.when(userDao.addUser(user)).thenReturn(Optional.of(1L));
        Long result = userService.addUser(user);

        assertNotNull(result);
    }

    @Test
    public void testAddUser_UserEquals_Success() {
        User user = createTestUser();

        Mockito.when(userDao.addUser(user)).thenReturn(Optional.of(1L));
        Long result = userService.addUser(user);

        assertEquals(result, (Long) 1L);
    }

    @Test
    public void testAddUser_UserNull_Fail() {
        Mockito.when(userDao.addUser(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> userService.addUser(null));
        verifyZeroInteractions(userDao);
    }

    @Test
    public void testDeleteUser_UserIdNotNull_Success() {
        Mockito.when(userDao.deleteUser(anyLong())).thenReturn(true);

        Boolean result = userService.deleteUser(anyLong());

        assertEquals(true, result);
    }

    @Test
    public void testDeleteUser_UserIdNull_Fail() {
        Mockito.when(userDao.deleteUser(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(null));
        verifyZeroInteractions(userDao);
    }

    @Test
    public void testDeleteUser_EqualResult_Success(){
        Mockito.when(userDao.deleteUser(anyLong())).thenReturn(true);
        Boolean result = userService.deleteUser(anyLong());

        assertEquals(result, true);
    }

    @Test
    public void testFindAllUsers_AllUsersPresent_Success() {

        List<User> result = userService.findAllUsers();

        assertNotNull(result);
    }

    @Test
    public void testFindUserById_UserIdIsCorrect() {
        User user = createTestUser();
        when(userDao.findUserById(any())).thenReturn(user);

        User result = userService.findUserById(anyLong());

        assertNotNull(result);
    }

    @Test
    public void testFindUserById_UserIdIsNull_Fail() {
        when(userDao.findUserById(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> userService.findUserById(null));
    }

    @Test
    public void  testEditUser_UserIsCorrect_Success() {
        User user = createTestUser();
        when(userDao.editUser(user)).thenReturn(true);

        Boolean result = userService.editUser(user);

        assertEquals(true, result);
    }

    @Test
    public void testEditUser_UserIsNull_Fail() {
        when(userDao.editUser(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,() -> userService.editUser(null));
    }

    @Test
    public void testEditUser_UserIdIsNull_Fail() {
        User user = createTestUser();
        user.setUserId(null);

        when(userDao.editUser(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,() -> userService.editUser(null));
    }

    @Test
    public void testEditUser_UserIdIsLessThanZero_Fail() {
        User user = createTestUser();
        user.setUserId(-2L);
        when(userDao.editUser(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,() -> userService.editUser(null));
    }
    private User createTestUser() {
        User user = new User();
        user.setUserId(1L);
        user.setPassword("abc");
        user.setName("Yuri");
        user.setUserType("user");
        return user;
    }
}
