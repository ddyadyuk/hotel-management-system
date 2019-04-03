package com.epam.edu.htm.core.dao;

import com.epam.edu.htm.model.User;

import java.util.Optional;

/**
 * The UserDao interface.
 *
 * <P>various behavior methods of user </P>
 *
 * @author Dmitry Dyadyuk
 * @version 1.0
 */
public interface UserDao {
    /**
     * Add user in database.
     *
     * @param user the user
     * @return user id
     */
    Long addUser(Optional<User> user);

    /**
     * Delete user boolean.
     *
     * @param id the id
     * @return the boolean
     */
    Boolean deleteUser(Long id);
}
