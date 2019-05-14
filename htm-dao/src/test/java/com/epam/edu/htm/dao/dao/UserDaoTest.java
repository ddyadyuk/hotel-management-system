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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDaoTest {
    @Mock
    private UserDao userDao;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Mock
    private RowMapper rowMapper;
    @InjectMocks
    private JdbcUserDao jdbcUserDao;

    public UserDaoTest() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void after() {
        verifyNoMoreInteractions(userDao);
        reset(userDao);
    }

    @Test
    public void addUser_UserNotNull_Success() {
        User user = createTestUser();

        when(jdbcTemplate.update(any(), Mockito.any(MapSqlParameterSource.class), Mockito.any(GeneratedKeyHolder.class),
                any(String[].class))).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Map<String, Object> keyMap = new HashMap<String, Object>();
                keyMap.put("", 1L);
                ((GeneratedKeyHolder) args[2]).getKeyList().add(keyMap);
                return 1;
            }
        });
        Optional<Long> result = jdbcUserDao.addUser(user);

        assertEquals((Optional.of(1L)), result);
        assertNotNull(result);
    }

    @Test
    public void testAddUser_UserIsNull_Fail() {
        assertThrows(IllegalArgumentException.class, () -> jdbcUserDao.addUser(null));
    }

    @Test
    public void testAddUser_EmptyResultWasThrown_Fail() {
        User user = createTestUser();

        when(jdbcTemplate.update(any(), Mockito.any(MapSqlParameterSource.class), Mockito.any(GeneratedKeyHolder.class),
                any(String[].class))).thenThrow(EmptyResultDataAccessException.class);

        assertThrows(EmptyResultDataAccessException.class,() -> jdbcUserDao.addUser(user));
    }
    @Test
    public void testDeleteUser_UserIdNotNull_Success() {

        when(jdbcTemplate.update(any(), Mockito.any(MapSqlParameterSource.class))).thenReturn(1);

        assertTrue(jdbcUserDao.deleteUser(1L));
        assertNotNull(jdbcUserDao.deleteUser(1L));
    }

    @Test
    public void testDeleteUser_UserIsNull_Fail() {

        assertThrows(IllegalArgumentException.class, () -> jdbcUserDao.deleteUser(null));
    }

    @Test
    public void testDeleteUser_EmptyResultWasThrown_Fail() {

        when(jdbcTemplate.update(any(), Mockito.any(MapSqlParameterSource.class))).thenThrow(EmptyResultDataAccessException.class);

        assertThrows(EmptyResultDataAccessException.class, () -> jdbcUserDao.deleteUser(1l));
    }

    @Test
    public void testFindAllUsers_UsersIsPresent_Success() throws SQLException {
//        when(jdbcTemplate.query(anyString(), any(UserRowMapper.class))).thenAnswer((Answer<User>) invocationOnMock -> {
//            Object[] args = invocationOnMock.getArguments();
//            RowMapper<User> rm = (RowMapper<User>) args[1];
//            ResultSet rs = mock(ResultSet.class);
//            return new User();
//        });

        List<User> result = jdbcUserDao.findAllUsers();

        assertNotNull(result);
//        assertEquals(1, result.size());

    }

    @Test
    public void testFindUserById_UserIdIsCorrect_Success() {
        User user = createTestUser();

        when(jdbcTemplate.queryForObject(any(), any(MapSqlParameterSource.class), any(RowMapper.class))).thenReturn(user);

        User result = jdbcUserDao.findUserById(1L);

        assertNotNull(result);

    }

    @Test
    public void testFindUserById_UserIdIsNull_Fail() {
        when(jdbcTemplate.queryForObject(any(), any(MapSqlParameterSource.class), any(RowMapper.class))).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> jdbcUserDao.findUserById(null));
    }

    @Test
    public void testFindUserById_UserAbsentInDB_Fail() {
        when(jdbcTemplate.queryForObject(any(), any(MapSqlParameterSource.class), any(RowMapper.class))).thenThrow(EmptyResultDataAccessException.class);

        assertThrows(EmptyResultDataAccessException.class, () -> jdbcUserDao.findUserById(20L));
    }

    @Test
    public void testEditUser_userIsOk_Success() {
        User user = createTestUser();
        user.setUserId(1L);

        when(jdbcTemplate.update(any(), any(MapSqlParameterSource.class))).thenReturn(1);

        Boolean result = jdbcUserDao.editUser(user);
        assertNotNull(result);
        assertEquals(true, result);
    }

    @Test
    public void testEditUser_UserIsNull_Fail() {
        assertThrows(IllegalArgumentException.class, () -> jdbcUserDao.editUser(null));
    }

    @Test
    public void testEditUser_UpdateValueIsZero_fail() {
        User user = createTestUser();
        user.setUserId(1L);

        when(jdbcTemplate.update(any(), any(MapSqlParameterSource.class))).thenReturn(0);

        assertEquals(false, jdbcUserDao.editUser(user));
    }

    @Test
    public void testEditUser_DataAccessWasThrown_Fail() {
        User user = createTestUser();

        when(jdbcTemplate.update(any(), any(MapSqlParameterSource.class))).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> jdbcUserDao.editUser(user));
    }

    private User createTestUser() {
        User user = new User();
        user.setPassword("abc");
        user.setName("Yuri");
        user.setUserType("user");
        return user;
    }
}
