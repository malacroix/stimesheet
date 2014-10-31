/**
 * 
 */
package ca.lc.stimesheet.web.login;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import ca.lc.stimesheet.model.User;
import ca.lc.stimesheet.web.login.userdetails.TimesheetUserDetails;

/**
 * On the user Logout, it will do the logout on the Marketplace.
 * 
 * @author Marc-Andre Lacroix
 *
 */
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    private static final Logger log = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException,
            ServletException {
        
        // Must also logout from Marketplace
        boolean redirected = false;
        if (authentication != null && authentication instanceof OpenIDAuthenticationToken) {
            OpenIDAuthenticationToken token = (OpenIDAuthenticationToken) authentication;
            
            if (token.getPrincipal() instanceof TimesheetUserDetails) {
                User currentUser = ((TimesheetUserDetails) token.getPrincipal()).getInternalUser();
                
                String marketBaseUrl = currentUser.getFromMarketplace().getBaseUrl();
                
                try {
                    URIBuilder uriBuilder = new URIBuilder(marketBaseUrl + "/applogout");
                    uriBuilder.addParameter("openid", currentUser.getOpenId());
                    
                    response.sendRedirect(uriBuilder.toString());
                    
                    redirected = true;
                } catch (URISyntaxException use) {
                    log.error("Could not generate the Marketplace logout URL appropriately.", use);
                }
            }
        }
        
        // If could not logout from MarketPlace, simply do to homepage
        if (!redirected) {
            response.sendRedirect("/");
        }
    }

}
