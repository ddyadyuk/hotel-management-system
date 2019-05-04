package com.epam.edu.htm.client;

import com.epam.edu.htm.client.dao.UserRestDao;
import com.epam.edu.htm.client.dto.UserRestDto;
import com.epam.edu.htm.client.service.impl.UserRestService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    private UserRestDto createTestUserRestDto() {
        UserRestDto user = new UserRestDto();
        user.setPassword("abc");
        user.setName("Yuri");
        user.setUserType("user");

        return user;
    }
}
