/**
 * 
 */
package ca.lc.stimesheet.web.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationStatus;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import ca.lc.stimesheet.web.EventController;

/**
 * @author Marc-Andre Lacroix
 *
 */
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final Logger log = LoggerFactory.getLogger(LoginFailureHandler.class);
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)  throws IOException, ServletException {
        log.error("onAuthenticationFailure", exception);
        
        // TODO : refactor error handling here as it might not be proper
        if (exception instanceof UsernameNotFoundException  && exception.getAuthentication() instanceof  OpenIDAuthenticationToken) {

            OpenIDAuthenticationToken token = (OpenIDAuthenticationToken) exception.getAuthentication();

            if (OpenIDAuthenticationStatus.SUCCESS.equals(token.getStatus())) {

                // getting attributes passed by openID provider

                final List<OpenIDAttribute> attrList = token.getAttributes();

                String username = (String) token.getPrincipal();

                //provide implementation to create user from information passed from 

                //openID provider and save this user in database

                //then redirect to redirectURL.

                DefaultRedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

                redirectStrategy.sendRedirect(request, response, "thymeleaf/welcome");

            } else {

                super.onAuthenticationFailure(request, response, exception);
            }
        }

    }
    
}
