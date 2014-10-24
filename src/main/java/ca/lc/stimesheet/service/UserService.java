/**
 * 
 */
package ca.lc.stimesheet.service;

import ca.lc.stimesheet.model.User;

/**
 * @author Marc-Andre Lacroix
 *
 */
public interface UserService {

    /**
     * Retrieves a user by its UUID.
     * @param uuid
     * @return User if found, else null.
     */
    User findByUuid(String uuid);
}
