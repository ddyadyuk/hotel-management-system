package com.epam.edu.htm.core;

import com.epam.edu.htm.core.dao.UserDao;
import com.epam.edu.htm.core.service.impl.UserService;
import com.epam.edu.htm.model.Address;
import com.epam.edu.htm.model.Contact;
import com.epam.edu.htm.model.User;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class UserServiceTest {
    private static final Long EXPECTED_ID = 1L;
    @Mock
    private  UserDao userDao;
    @InjectMocks
    private UserService userService;

    public UserServiceTest(){
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void after() {
        verifyNoMoreInteractions(userDao);
        Mockito.reset(userDao);
    }

    @Test
    public void testAddUser_userNotNull_success() {
        User user = createTestUser();

        Mockito.when(userDao.addUser(user)).thenReturn(Optional.of(EXPECTED_ID));
        Optional<Long> result = userService.addUser(user);

        Mockito.verify(userDao,Mockito.times(1)).addUser(user);
    }

    @Test
    public void testAddUser_userNotNull_responseNotnull() {
        Optional<Long> result = userService.addUser(createTestUser());

        assertNotNull(result);
    }

    @Test
    public void testAddUser_userEquals_success(){
        User user = createTestUser();

        Mockito.when(userDao.addUser(user)).thenReturn(Optional.of(EXPECTED_ID));
        Optional<Long> result = userService.addUser(user);

        assertEquals(Optional.of(1L),result);
    }

    @Test
    public void testAddUser_userNull_fail(){
        Mockito.when(userDao.addUser(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () ->
                userService.addUser(null));
        verifyZeroInteractions(userDao);
    }

    @Test
    public void testDeleteUser_userIdNotNull_success() {
        Mockito.when(userDao.deleteUser(anyLong())).thenReturn(true);

        Boolean result = userService.deleteUser(anyLong());

        assertEquals(true, result);
    }

    @Test
    public void testDeleteUser_userIdNull_fail() {
        Mockito.when(userDao.deleteUser(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () ->
                userService.deleteUser(null));
        verifyZeroInteractions(userDao);
    }

    private User createTestUser() {
        User user = new User();
        user.setPassword("abc");
        user.setContact(new Contact());
        user.setAddress(new Address());
        user.setUserType("user");
    return user;
    }
}
