package com.epam.edu.htm.core.service.impl;

import com.epam.edu.htm.core.dao.UserDao;
import com.epam.edu.htm.core.service.UserOperations;
import com.epam.edu.htm.model.User;

import java.util.List;

public class UserService implements UserOperations {
    private UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public Long addUser(User user) {

        if (user == null) {
            throw new IllegalArgumentException("Parameter 'user' can't be null");
        }

        return dao.addUser(user)
                .orElseThrow(() -> new IllegalArgumentException("The value is not present"));
    }

    @Override
    public Boolean deleteUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Parameter 'id' can't be null");
        }
        return dao.deleteUser(id);
    }

    @Override
    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

    @Override
    public User findUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Parameter 'id can't be null'");
        }

        return dao.findUserById(id);
    }


    @Override
    public Boolean editUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Parameter 'userId' can't be null or lower than 0");
        }
        return dao.editUser(user);

    }

}
