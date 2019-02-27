package com.epam.edu.htm.core.dao;

import com.epam.edu.htm.model.User;

public interface UserDao {
    Long addUser(User user);
    Boolean deleteUser(Long id);
}
