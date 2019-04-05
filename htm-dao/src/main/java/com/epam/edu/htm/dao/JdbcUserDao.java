package com.epam.edu.htm.dao;

import com.epam.edu.htm.core.dao.UserDao;
import com.epam.edu.htm.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JdbcUserDao implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUserDao.class);

    private static final String USER_CONTACT_ID = "contact_id";
    private static final String USER_ADDRESS_ID = "address_id";
    private static final String USER_TYPE = "user_type";
    private static final String USER_PASSWORD = "password";

    @Value("${create_user}")
    private String insert;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public JdbcUserDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Long addUser(User user) {

        if (user == null) {
            throw new IllegalArgumentException("Parameter 'user' can't be null");
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue(USER_PASSWORD, user.getPassword())
                .addValue(USER_CONTACT_ID, user.getContact().getContactId())
                .addValue(USER_ADDRESS_ID, user.getAddress().getAddressId())
                .addValue(USER_TYPE, user.getUserType());

        int rowNumber = 0;
        try {
            rowNumber = namedParameterJdbcTemplate.update(insert, mapSqlParameterSource, keyHolder);
        } catch (DataAccessException e) {
            LOGGER.warn("Could not save user.", e);
        }

        if (rowNumber > 0) {
            return Objects.requireNonNull(keyHolder.getKey()).longValue();
        }
        return null;
    }

    @Override
    public Boolean deleteUser(Long id) {
        return true;
    }
}