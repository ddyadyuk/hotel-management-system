package com.epam.edu.htm.dao;

import com.epam.edu.htm.core.dao.UserDao;
import com.epam.edu.htm.model.Address;
import com.epam.edu.htm.model.Contact;
import com.epam.edu.htm.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {
    private static Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    private static final String USER_CONTACT_ID = "contact_id";
    private static final String USER_ADDRESS_ID = "address_id";
    private static final String USER_TYPE = "user_type";
    private static final String USER_PASSWORD = "password";
    private static final String FIRST_ADDRESS = "first_address";
    private static final String SECOND_ADDRESS = "second_address";
    private static final String THIRD_ADDRESS = "third_address";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String POSTAL_CODE = "postal_code";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";

    @Value("${create_user}")
    private String insert;
    @Value("${insert_address}")
    private String insert_address;
    @Value("${insert_contact}")
    private String insert_contact;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Autowired
    public UserDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Long addUser(User user) {
        insertContact(user.getContact());
        insertAddress(user.getAddress());

        LOGGER.debug("Add user({})", user);
        insertUser(user).orElseThrow(() ->
                new IllegalArgumentException("There is no user"));
        return user.getUserId();
    }

    private Optional<User> insertUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource
                .addValue(USER_PASSWORD, user.getPassword())
                .addValue(USER_CONTACT_ID, user.getContact().getIdContact())
                .addValue(USER_ADDRESS_ID, user.getAddress().getAddressId())
                .addValue(USER_TYPE, user.getUserType());
        namedParameterJdbcTemplate.update( insert, mapSqlParameterSource, keyHolder);
        user.setUserId(keyHolder.getKey().longValue());
        return Optional.of(user);
    }
    private Long insertAddress(Address address){
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue(FIRST_ADDRESS, address.getFirsAddress());
        sqlParameterSource.addValue(SECOND_ADDRESS, address.getSecondAddress());
        sqlParameterSource.addValue(THIRD_ADDRESS, address.getThirdAddress());
        sqlParameterSource.addValue(CITY, address.getCity());
        sqlParameterSource.addValue(STREET, address.getStreet());
        sqlParameterSource.addValue(POSTAL_CODE, address.getPostalCode());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        long result = (long) namedParameterJdbcTemplate.update( insert_address, sqlParameterSource, keyHolder);
        address.setAddressId(keyHolder.getKey().longValue());
        return result;
    }
    private Long insertContact(Contact contact){
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue(PHONE, contact.getPhone());
        sqlParameterSource.addValue(EMAIL, contact.getEmail());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        long result= (long) namedParameterJdbcTemplate.update(insert_contact, sqlParameterSource, keyHolder);
        contact.setIdContact(keyHolder.getKey().longValue());
        return result;
    }
    @Override
    public Boolean deleteUser(Long id) {
        return null;
    }
}
