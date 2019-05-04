package com.epam.edu.htm.client.service;

import com.epam.edu.htm.client.dto.UserRestDto;

import java.util.List;


/**
 * The interface User rest service.
 */
public interface UserRestOperations {

    /**
     * Add user long.
     *
     * @param user the user
     * @return the long
     */
    Long addUser(UserRestDto user);

    /**
     * Delete user boolean.
     *
     * @param id the id
     * @return the boolean
     */
    Boolean deleteUser(Long id);


    /**
     * Find all users list.
     *
     * @return the list
     */
    List<UserRestDto> findAllUsers();


    /**
     * Find user by id user.
     *
     * @param id the id
     * @return the user
     */
    UserRestDto findUserById(Long id);

    /**
     * Edit user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    Boolean editUser(UserRestDto user);
}
