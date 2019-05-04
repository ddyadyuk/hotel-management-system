package com.epam.edu.htm.client.dao;

import com.epam.edu.htm.client.dto.UserRestDto;

import java.util.List;
import java.util.Optional;

/**
 * The interface Rest user dao.
 */
public interface UserRestDao {

    /**
     * Add user optional.
     *
     * @param user the user
     * @return the optional
     */
    Optional<Long> addUser(UserRestDto user);

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
     * Find user by id user dto.
     *
     * @param id the id
     * @return the user dto
     */
    Optional<UserRestDto> findUserById(Long id);

    /**
     * Edit user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    Boolean editUser(UserRestDto user);

}
