package com.epam.edu.htm.core.service;

import com.epam.edu.htm.model.User;

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
     * @param id the user id
     * @return the boolean state
     */
    Boolean deleteUser(Long id);
}
