package com.epam.edu.htm.dao.dao;

import com.epam.edu.htm.core.dao.UserDao;
import com.epam.edu.htm.dao.JdbcUserDao;
import com.epam.edu.htm.model.User;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UserDaoTest {
    @Mock
    private UserDao userDao;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @InjectMocks
    private JdbcUserDao jdbcUserDao;

    public UserDaoTest(){
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void after(){
        verifyNoMoreInteractions(userDao);
        reset(userDao);
    }

    @Test
    public void addUser_UserNotNull_success(){
        User user = createTestUser();

        when(jdbcTemplate.update(any(), Mockito.any(MapSqlParameterSource.class), Mockito.any(GeneratedKeyHolder.class),
                any(String[].class))).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Map<String, Object> keyMap = new HashMap<String, Object>();
                keyMap.put("", 1L);
                ((GeneratedKeyHolder)args[2]).getKeyList().add(keyMap);
                return 1;
            }
        });
       Long result = jdbcUserDao.addUser(user);

       assertEquals((Long) 1L, result);
    }

    @Test
    public void addUser_IsUserNotNull_success(){
        User user = createTestUser();

        when(jdbcTemplate.update(any(), Mockito.any(MapSqlParameterSource.class),
                Mockito.any(GeneratedKeyHolder.class),any(String[].class))).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Map<String, Object> keyMap = new HashMap<String, Object>();
                keyMap.put("", 1L);
                ((GeneratedKeyHolder)args[2]).getKeyList().add(keyMap);
                return 1;
            }
        });
        Long result = jdbcUserDao.addUser(user);

        assertNotNull(result);
    }
    private User createTestUser(){
        User user = new User();
        user.setPassword("abc");
//        user.setContact(new Contact());
//        user.setAddress(new Address());
        user.setUserType("user");
        return user;
    }
}
