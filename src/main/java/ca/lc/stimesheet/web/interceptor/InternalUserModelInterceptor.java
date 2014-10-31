/**
 * 
 */
package ca.lc.stimesheet.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ca.lc.stimesheet.web.login.userdetails.TimesheetUserDetails;

/**
 * Interceptor to set the user and authentication attributes in the Model for easier retrieval in views.
 * 
 * @author Marc-Andre Lacroix
 *
 */
public class InternalUserModelInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        // No need to do anything if we don't have the model
        if (modelAndView!= null) {

            Authentication auth= SecurityContextHolder.getContext().getAuthentication();
            
            if (auth != null && auth.getPrincipal() != null && auth.getPrincipal() instanceof TimesheetUserDetails) {
                modelAndView.addObject("user", ((TimesheetUserDetails) auth.getPrincipal()).getInternalUser());
                modelAndView.addObject("authenticated", true);
            } else {
                modelAndView.addObject("authenticated", false);
            }

        }
    }


}
