package com.epam.edu.htm.core.dao;

import com.epam.edu.htm.model.User;

import java.util.List;
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
     * <P>method for adding users</P>
     *
     * @param user the user
     * @return user id
     */
    Optional<Long> addUser(User user);

    /**
     * Delete user boolean.
     *
     * <P>method for deleting users</P>
     *
     * @param id the id
     * @return the boolean
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
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
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
