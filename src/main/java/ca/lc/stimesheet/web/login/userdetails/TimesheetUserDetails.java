/**
 * 
 */
package ca.lc.stimesheet.web.login.userdetails;

import java.util.ArrayList;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import ca.lc.stimesheet.model.User;



/**
 * @author Marc-Andre Lacroix
 *
 */
public class TimesheetUserDetails extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 3452519087909202050L;
    
    private User internalUser;
    
    /**
     * Creates a Timesheet user details with the actual {@link User} infos.
     * @param internalUser
     */
    public TimesheetUserDetails(User internalUser) {
        super(internalUser.getUuid(), "", new ArrayList<SimpleGrantedAuthority>());
        this.internalUser = internalUser;
    }
    
    /**
     * @return the internalUser
     */
    public User getInternalUser() {
        return internalUser;
    }
}
