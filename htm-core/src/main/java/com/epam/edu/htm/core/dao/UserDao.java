package com.epam.edu.htm.core.dao;

import com.epam.edu.htm.model.User;

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
    Long addUser(User user);

    /**
     * @param id the user id
     * @return the boolean state
     */
    Boolean deleteUser(Long id);
}
