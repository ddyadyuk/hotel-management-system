package com.epam.edu.htm.core.service;

import com.epam.edu.htm.model.User;

public interface UserOperations {
    Long addUser(User user);
    Boolean deleteUser(Long id);
}
