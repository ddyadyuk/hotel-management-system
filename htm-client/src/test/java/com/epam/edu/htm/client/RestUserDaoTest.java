package com.epam.edu.htm.client;

import com.epam.edu.htm.client.dao.UserRestDao;
import com.epam.edu.htm.client.dao.impl.RestUserDao;
import com.epam.edu.htm.client.dto.UserRestDto;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class RestUserDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestUserDaoTest.class);

    @Mock
    private UserRestDao userDao;
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private RestUserDao restUserDao;

    private static final String USER_WITHOUT_PARAMETERS = "http://localhost:8000/htm_rest_app_war_exploded/";
    private static final String USER_WITH_PARAMETERS = "http://localhost:8000/htm_rest_app_war_exploded/{id}";

    private UserRestDto userRestDto;

    public RestUserDaoTest() {
        MockitoAnnotations.initMocks(this);
    }


    @Before
    public void setUp() {
        userRestDto = new UserRestDto(1L,"qwerty", "John", "user");
    }

    @AfterEach
    public void after() {
        verifyNoMoreInteractions(userDao);
        reset(userDao);
    }

    @Test
    public void testAddUser_UserIsFine_Success() {
        when(restTemplate.postForObject(USER_WITHOUT_PARAMETERS, userRestDto, Long.class))
                .thenReturn(1L);
        Optional<Long> result = restUserDao.addUser(userRestDto);

        assertNotNull(result);
        assertEquals(result, Optional.of(1L));
    }

    @Test
    public void testAddUser_HttpClientErrorExceptionNotFound_Empty() {
        when(restTemplate.postForObject(USER_WITHOUT_PARAMETERS, userRestDto, Long.class))
                .thenThrow(HttpClientErrorException.NotFound.class);

        assertEquals(Optional.empty(), restUserDao.addUser(userRestDto));
    }

    @Test
    public void testDeleteUser_IdIsFine_Success() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<Boolean> responseEntity = new ResponseEntity<>(true, HttpStatus.OK);

        when(restTemplate.exchange(USER_WITH_PARAMETERS, HttpMethod.DELETE, entity, Boolean.class, 1L))
                .thenReturn(responseEntity);
        Boolean result = restUserDao.deleteUser(1L);

        assertNotNull(result);
        assertEquals(true, result);
    }

    @Test
    public void testDeleteUSer_HttpClientErrorExceptionNotFound_False() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        when(restTemplate.exchange(USER_WITH_PARAMETERS, HttpMethod.DELETE, entity, Boolean.class, 1L))
                .thenThrow(HttpClientErrorException.NotFound.class);
        Boolean result = restUserDao.deleteUser(1L);

        assertNotNull(result);
        assertEquals(false, result);
    }

    @Test
    public void testFindAllUsers_AllUsersPresent_Success() {
        when(restTemplate.getForObject(USER_WITHOUT_PARAMETERS, UserRestDto[].class))
                .thenReturn(new UserRestDto[]{userRestDto, userRestDto, userRestDto});
        List<UserRestDto> result = restUserDao.findAllUsers();

        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    public void testFindAllUsers_HttpClientErrorExceptionNotFound_EmptyList() {
        when(restTemplate.getForObject(USER_WITHOUT_PARAMETERS, UserRestDto[].class))
                .thenThrow(HttpClientErrorException.NotFound.class);
        List<UserRestDto> result = restUserDao.findAllUsers();

        assertNotNull(result);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testFindUserById_IdIsFine_Success() {
        UserRestDto userRestDto = new UserRestDto(1L, "qwerty", "John", "user");
        LOGGER.debug("userRestDto is: {}", userRestDto);

        when(restTemplate.getForObject(USER_WITH_PARAMETERS, UserRestDto.class, 1L))
                .thenReturn(userRestDto);
        Optional<UserRestDto> result = restUserDao.findUserById(1L);

        assertNotNull(result);
        assertEquals(Optional.of(userRestDto), result);
    }

    @Test
    public void testFindUserById_HttpClientErrorExceptionNotFound_OptionalEmpty() {
        when(restTemplate.getForObject(USER_WITH_PARAMETERS, UserRestDto.class, 1L))
                .thenThrow(HttpClientErrorException.NotFound.class);

        Optional<UserRestDto> result = restUserDao.findUserById(1L);

        assertNotNull(result);
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testEditUSer_UserIfIsFine_Success() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<Boolean> responseEntity = new ResponseEntity<>(true, HttpStatus.OK);

        when(restTemplate.exchange(USER_WITHOUT_PARAMETERS, HttpMethod.PUT, entity, Boolean.class))
                .thenReturn(responseEntity);
        Boolean result = restUserDao.editUser(userRestDto);

        assertNotNull(result);
        assertEquals(true, result);
    }

    @Test
    public void testEditUser_HttpClientErrorExceptionNotFound_False() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        when(restTemplate.exchange(USER_WITHOUT_PARAMETERS, HttpMethod.PUT, entity, Boolean.class))
                .thenThrow(HttpClientErrorException.NotFound.class);
        Boolean result = restUserDao.editUser(userRestDto);

        assertNotNull(result);
        assertEquals(false, result);

    }

}
