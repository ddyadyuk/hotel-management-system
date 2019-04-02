package com.epam.edu.htm.core.service.impl;

import com.epam.edu.htm.core.dao.UserDao;
import com.epam.edu.htm.core.service.UserOperations;
import com.epam.edu.htm.model.User;

import java.util.Optional;

public class UserService implements UserOperations {
    private UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

        public Optional<Long> addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Parameter 'user' can't be null");
        }
        return dao.addUser(user);
    }

    @Override
    public Boolean deleteUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Parameter 'id' can't be null");
        }
        dao.deleteUser(id);
        return true;
    }
}
