package com.epam.edu.htm.client.service.impl;

import com.epam.edu.htm.client.dao.UserRestDao;
import com.epam.edu.htm.client.dto.UserRestDto;
import com.epam.edu.htm.client.service.UserRestOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRestService implements UserRestOperations {

    private UserRestDao restUserDao;

    @Autowired
    public UserRestService(UserRestDao restUserDao) {
        this.restUserDao = restUserDao;
    }

    @Override
    public Long addUser(UserRestDto user) {
        if (user == null) {
            throw new IllegalArgumentException("Parameter 'user' cannot be null");
        }

        return restUserDao.addUser(user).get();
    }

    @Override
    public Boolean deleteUser(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Parameter 'id' cannot be null and greater than null");
        }

        return restUserDao.deleteUser(id);
    }

    @Override
    public List<UserRestDto> findAllUsers() {
        return restUserDao.findAllUsers();
    }

    @Override
    public UserRestDto findUserById(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Parameter 'id' cannot be null and greater than null");
        }

        return restUserDao.findUserById(id).get();
    }

    @Override
    public Boolean editUser(UserRestDto user) {
        if (user == null) {
            throw new IllegalArgumentException("Parameter 'user' cannot be null");
        }

        return restUserDao.editUser(user);
    }
}
