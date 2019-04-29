package com.epam.edu.htm.dao.mapper;

import com.epam.edu.htm.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class UserRowMapper implements RowMapper<User> {

    private static final String USER_TYPE = "user_type";
    private static final String USER_PASSWORD = "password";
    private static final String ID_USER = "id_user";
    private static final String USER_NAME="user_name";

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getLong(ID_USER));
        user.setPassword(rs.getString(USER_PASSWORD));
        user.setName(rs.getString(USER_NAME));
        user.setUserType(rs.getString(USER_TYPE));
        return user;
    }
}
