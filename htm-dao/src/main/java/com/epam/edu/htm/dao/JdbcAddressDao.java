package com.epam.edu.htm.dao;

import com.epam.edu.htm.core.dao.AddressDao;
import com.epam.edu.htm.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class JdbcAddressDao implements AddressDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcAddressDao.class);

    private static final String ADDRESS_ID = "id_address";
    private static final String ADDRESS1 = "address1";
    private static final String ADDRESS2 = "address2";
    private static final String ADDRESS3 = "address3";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String POSTAL_CODE = "postal_code";
    private static final String ADDRESS_ID_INFORMATION_MESSAGE = "There are no Address with corresponding id";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${add_address}")
    private String addAddressQuery;
    @Value("${update_address}")
    private String updateAddressQuery;

    @Autowired
    public JdbcAddressDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public Optional<Long> addAddress(Address address) {

        if (address == null) {
            throw new IllegalArgumentException("Parameter 'address' can not be null");
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(ADDRESS1, address.getFirsAddress())
                .addValue(ADDRESS2, address.getSecondAddress())
                .addValue(ADDRESS3, address.getThirdAddress())
                .addValue(CITY, address.getCity())
                .addValue(STREET, address.getStreet())
                .addValue(POSTAL_CODE, address.getPostalCode());

        int rowNumber = 0;
        try {
            rowNumber = namedParameterJdbcTemplate.update(addAddressQuery, mapSqlParameterSource, keyHolder,
                    new String[]{ADDRESS_ID});
            if (rowNumber == 0) {
                throw new EmptyResultDataAccessException(rowNumber);
            }
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("This Address can't be added", e);
            throw e;
        }

        return Optional.of(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }

    @Override
    public Boolean editAddress(Address address) {

        if (address == null) {
            throw new IllegalArgumentException("Parameter 'id' can not be null");
        }

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(ADDRESS_ID, address.getAddressId())
                .addValue(ADDRESS1, address.getFirsAddress())
                .addValue(ADDRESS2, address.getSecondAddress())
                .addValue(ADDRESS3, address.getThirdAddress())
                .addValue(CITY, address.getCity())
                .addValue(STREET, address.getStreet())
                .addValue(POSTAL_CODE, address.getPostalCode());

        int rowNumber = 0;
        try {
            rowNumber = namedParameterJdbcTemplate.update(updateAddressQuery, mapSqlParameterSource);
            if (rowNumber == 0) {
                throw new EmptyResultDataAccessException(rowNumber);
            }
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug(ADDRESS_ID_INFORMATION_MESSAGE, e);
            throw e;
        }

        return true;
    }

    @Override
    public List<Address> findAllAddresses() {
        return null;
    }

    @Override
    public Address findAddressById(Long id) {
        return null;
    }
}
