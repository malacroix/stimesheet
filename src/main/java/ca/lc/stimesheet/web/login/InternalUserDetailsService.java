/**
 * 
 */
package ca.lc.stimesheet.web.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ca.lc.stimesheet.model.User;
import ca.lc.stimesheet.service.UserSubscriptionService;
import ca.lc.stimesheet.web.login.userdetails.TimesheetUserDetails;

/**
 * @author Marc-Andre Lacroix
 *
 */
public class InternalUserDetailsService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(InternalUserDetailsService.class);

    @Autowired
    private UserSubscriptionService userSubscriptionService;
    
    @Override
    public UserDetails loadUserByUsername(String openid) throws UsernameNotFoundException {
        log.debug("loadUserByUsername({})", openid);
        
        // The openid is unique by Marketplace (it contains the marketplace base URL), so simply retrieve User by OpenId
        User userFound = userSubscriptionService.findUserByOpenId(openid);
        
        // To be valid, a User must exist and have an Active Subscription account
        if (userFound != null && userFound.isAccountActive()) {
            return new TimesheetUserDetails(userFound);
        } else {
            String errorMessage;
            if (userFound == null) {
                errorMessage = "The user does not exist.";
            } else {
                errorMessage = "The user has an inactive subscription or is not assigned to any subscription.";
            }
            throw new UsernameNotFoundException(errorMessage);
        }        
    }

}
