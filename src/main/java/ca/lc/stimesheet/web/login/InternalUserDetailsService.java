/**
 * 
 */
package ca.lc.stimesheet.web.login;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Marc-Andre Lacroix
 *
 */
public class InternalUserDetailsService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(InternalUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String openid) throws UsernameNotFoundException {
        log.debug("loadUserByUsername({})", openid);
        
        // The openid is unique by Marketplace (it contains the marketplace base URL), so simply retrieve User by OpenId
        
        return new User("test", "none", new ArrayList<SimpleGrantedAuthority>());
    }

}
