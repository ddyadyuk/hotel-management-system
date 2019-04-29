package com.epam.edu.htm.dao.mapper;

import com.epam.edu.htm.model.Address;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressMapper implements RowMapper<Address> {

    private static final String ID_ADDRESS = "id_address";
    private static final String ADDRESS1 ="address1";
    private static final String ADDRESS2 = "address2";
    private static final String ADDRESS3 = "address3";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String POSTAL_CODE = "postal_code";

    @Override
    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
        Address address = new Address();
        address.setAddressId(rs.getLong(ID_ADDRESS));
        address.setSecondAddress(rs.getString(ADDRESS2));
        address.setFirsAddress(rs.getString(ADDRESS1));
        address.setThirdAddress(rs.getString(ADDRESS3));
        address.setCity(rs.getString(CITY));
        address.setStreet(rs.getString(STREET));
        address.setPostalCode(rs.getString(POSTAL_CODE));
        return address;
    }


}
