package com.epam.edu.htm.core.service;

import com.epam.edu.htm.model.User;

import java.util.List;

/**
 * The UserOperations interface.
 *
 * <P>various behavior methods of user </P>
 *
 * @author Dmitry Dyadyuk
 * @version 1.0
 */
public interface UserOperations {

    /**
     * Add user in database.
     *
     * @param user the user
     * @return user id
     */
    Long addUser(User user);

    /**
     * Delete user boolean.
     *
     * @param id the user id
     * @return the boolean state
     */
    Boolean deleteUser(Long id);

    /**
     * Find all users list.
     *
     * <P>method for showing all users</P>
     *
     * @return the set
     */
    List<User> findAllUsers();

    /**
     * Find user byid user.
     *
     * @param id the id
     * @return the user
     */
    User findUserById(Long id);

    /**
     * Edit user boolean.
     *
     * <P>method for editing user</P>
     *
     * @param user the user
     * @return the boolean
     */
    Boolean editUser(User user);
}
