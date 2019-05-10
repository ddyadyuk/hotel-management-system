package com.epam.edu.htm.client;

import com.epam.edu.htm.client.dao.UserRestDao;
import com.epam.edu.htm.client.dto.UserRestDto;
import com.epam.edu.htm.client.service.impl.UserRestService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


public class RestServiceTest {

    @Mock
    private UserRestDao userRestDao;
    @InjectMocks
    private UserRestService userRestService;

    public RestServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void after() {
        verifyNoMoreInteractions(userRestDao);
        Mockito.reset(userRestDao);
    }

    @Test
    public void testAddUser_UserParameterIsOk_Success() {
        UserRestDto userRestDto = createTestUserRestDto();

        when(userRestDao.addUser(userRestDto)).thenReturn(Optional.of(1L));
        Long result = userRestService.addUser(userRestDto);

        assertNotNull(result);
        assertEquals((Long) 1L, result);
    }

    //To do: test addUser exception

    @Test
    public void testAddUSer_UserIsNull_Fail() {
        when(userRestDao.addUser(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> userRestDao.addUser(null));
    }

    @Test
    public void testDeleteUser_UserIdIsPresent_Success() {
        when(userRestDao.deleteUser(1L)).thenReturn(true);
        Boolean result = userRestService.deleteUser(1L);

        assertNotNull(result);
        assertEquals(true, result);
    }

    @Test
    public void testDeleteUser_IdIsNull_Fail() {
        when(userRestDao.deleteUser(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> userRestService.deleteUser(null));
    }

    @Test
    public void testDeleteUser_IdIsLessThanZero_Fail() {
        when(userRestDao.deleteUser(-1L)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> userRestService.deleteUser(-1L));
    }

    @Test
    public void testFindAllUsers_AllUsersPresent_Success() {
        List<UserRestDto> result = userRestService.findAllUsers();

        assertNotNull(result);
    }

    @Test
    public void testFindUserById_IdIsCorrect_Success() {
        UserRestDto user = createTestUserRestDto();

        when(userRestDao.findUserById(1L)).thenReturn(Optional.of(user));
        UserRestDto result = userRestService.findUserById(1L);

        assertNotNull(result);
    }

    @Test
    public void testFindUserById_IdIsNull_Fail() {
        when(userRestDao.findUserById(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> userRestService.findUserById(null));
    }

    //To Do: findUserById optional is not present exception

    @Test
    public void testFindUSerById_IdIsLessThanZero_Fail() {
        assertThrows(IllegalArgumentException.class, () -> userRestService.findUserById(-1L));
    }

    @Test
    public void testEditUser_UserIsCorrect_Success() {
        UserRestDto user = createTestUserRestDto();

        when(userRestDao.editUser(user)).thenReturn(true);
        Boolean result = userRestService.editUser(user);

        assertNotNull(result);
        assertEquals(true, result);
    }

    @Test
    public void testEditUser_UserIsNull_Fail() {
        when(userRestDao.editUser(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> userRestService.editUser(null));
    }

    private UserRestDto createTestUserRestDto() {
        UserRestDto user = new UserRestDto();
        user.setPassword("abc");
        user.setName("Yuri");
        user.setUserType("user");

        return user;
    }
}
