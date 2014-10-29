/**
 * 
 */
package ca.lc.stimesheet.web.login;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;

/**
 * @author Marc-Andre Lacroix
 *
 */
public class InternalAuthenticationUserDetailsService implements AuthenticationUserDetailsService<OpenIDAuthenticationToken> {
    private static final Logger log = LoggerFactory.getLogger(InternalAuthenticationUserDetailsService.class);


    @Override
    public UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException {
        log.debug("loadUserDetails({})", token);
        
        // The openid is unique by Marketplace (it contains the marketplace base URL), so simply retrieve User by OpenId
        
        return new User(token.getName(), "none", new ArrayList<SimpleGrantedAuthority>());
    }
    

}
