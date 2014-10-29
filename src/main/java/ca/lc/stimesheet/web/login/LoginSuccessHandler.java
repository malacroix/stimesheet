/**
 * 
 */
package ca.lc.stimesheet.web.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * @author Marc-Andre Lacroix
 *
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private static final Logger log = LoggerFactory.getLogger(LoginSuccessHandler.class);
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,  HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.debug("onAuthenticationSuccess({})", authentication);
        
        
        if (authentication instanceof OpenIDAuthenticationToken) {
            OpenIDAuthenticationToken openIdToken = (OpenIDAuthenticationToken) authentication;
            log.debug("We have an OpenId Token!");
        }
        
        //provide implementation to set user data in session
    
        //redirecting to landing page
    
        // TODO : must implement a proper secured page, not the public home
        getRedirectStrategy().sendRedirect(request, response, "/");

    }
}
