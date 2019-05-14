package com.epam.edu.htm.dao;

import com.epam.edu.htm.core.dao.UserDao;
import com.epam.edu.htm.dao.mapper.UserRowMapper;
import com.epam.edu.htm.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class JdbcUserDao implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUserDao.class);

    //    private static final String USER_CONTACT_ID = "contact_id";
//    private static final String USER_ADDRESS_ID = "address_id";
    private static final String USER_TYPE = "user_type";
    private static final String USER_PASSWORD = "password";
    private static final String ID_USER = "id_user";
    private static final String USER_NAME = "user_name";
    private static final String ID_PARAMETER_MESSAGE = "The 'id' parameter can't be null";

    @Value("${create_user}")
    private String insertQuery;
    @Value("${delete_user}")
    private String deleteQuery;
    @Value("${find_all_users}")
    private String findAllQuery;
    @Value("${update_user}")
    private String updateQuery;
    @Value("${find_by_id}")
    private String findByIdQuery;


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final UserRowMapper rowMapper = new UserRowMapper();

    @Autowired
    public JdbcUserDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<Long> addUser(User user) {

        if (user == null) {
            throw new IllegalArgumentException("Parameter 'user' can't be null");
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue(USER_PASSWORD, user.getPassword())
                .addValue(USER_NAME, user.getName())
                .addValue(USER_TYPE, user.getUserType());
//                .addValue(USER_CONTACT_ID, user.getContact().getContactId())
//                .addValue(USER_ADDRESS_ID, user.getAddress().getAddressId())

        int rowNumber;
        try {
            rowNumber = namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource, keyHolder,
                    new String[]{ID_USER});
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("Could not save user.", e);
            throw e;
        }

        if (rowNumber > 0) {
            return Optional.of(Objects.requireNonNull(keyHolder.getKey()).longValue());
        }
        return Optional.empty();
    }

    @Override
    public Boolean deleteUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(ID_PARAMETER_MESSAGE);
        }

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(ID_USER, id);
        int rowNumber;

        try {
            rowNumber = namedParameterJdbcTemplate.update(deleteQuery, mapSqlParameterSource);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("There is no user with corresponding 'id' : {}", id, e);
            throw e;
        }

        return rowNumber > 0;
    }

    @Override
    public List<User> findAllUsers() {
        return namedParameterJdbcTemplate.query(findAllQuery, rowMapper);
    }

    @Override
    public User findUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(ID_PARAMETER_MESSAGE);
        }

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource(ID_USER, id);

        User result;
        try {
            result = namedParameterJdbcTemplate.queryForObject(findByIdQuery, sqlParameterSource, rowMapper);
        } catch (DataAccessException e) {
            LOGGER.debug("There is no user with corresponding 'id' ");
            throw e;
        }

        return result;
    }

    @Override
    public Boolean editUser(User user) {
        if (user == null || user.getUserId() == null || user.getUserId() < 0) {
            throw new IllegalArgumentException(ID_PARAMETER_MESSAGE);
        }
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource
                .addValue(USER_PASSWORD, user.getPassword())
                .addValue(USER_NAME, user.getName())
                .addValue(USER_TYPE, user.getUserType())
                .addValue(ID_USER, user.getUserId());

        int rowNumber;
        try {
            rowNumber = namedParameterJdbcTemplate.update(updateQuery, mapSqlParameterSource);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("There are no user with corresponding 'id' : {} ", user.getUserId(), e);
            throw e;
        }
        if (rowNumber > 0) {
            return true;
        }
        return false;
    }
}