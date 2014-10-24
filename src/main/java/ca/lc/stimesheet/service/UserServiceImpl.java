/**
 * 
 */
package ca.lc.stimesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.lc.stimesheet.model.User;
import ca.lc.stimesheet.repository.UserRepository;

/**
 * @author Marc-Andre Lacroix
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    
    @Override
    public User findByUuid(String uuid) {
        return userRepository.findOne(uuid);
    }

}
