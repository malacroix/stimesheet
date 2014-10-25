/**
 * 
 */
package ca.lc.stimesheet.web.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Marc-Andre Lacroix
 *
 */
public class RequestLoggingInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        Map<String, String> requestParametersMap = this.loadRequestParametersAsMap(request);
        Map<String, String> requestHeadersMap = this.loadRequestHeadersAsMap(request);

        StringBuilder logMessage = new StringBuilder("REST Request - ")
                                            .append("[HTTP METHOD:").append(request.getMethod())                                        
                                            .append("] [PATH INFO:").append(request.getPathInfo())          
                                            .append("] [REQUEST HEADERS:").append(requestHeadersMap)
                                            .append("] [REQUEST PARAMETERS:").append(requestParametersMap)                                
                                            .append("] [REMOTE ADDRESS:").append(request.getRemoteAddr())
                                            .append("]");

        log.debug("INCOMING REQUEST: {}", logMessage);
        
        // Continue normally
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Nothing special to do
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Nothing special to do
    }
    
    /**
     * Loads the request parameters in a map.
     * Will only show the first parameter value if more than 1 parameter with same name exist.
     * @param request
     * @return
     */
    private Map<String, String> loadRequestParametersAsMap(HttpServletRequest request) {
        Map<String, String> parametersMap = new HashMap<String, String>();
        Enumeration<?> requestParamNames = request.getParameterNames();
        while (requestParamNames.hasMoreElements()) {
            String requestParamName = (String) requestParamNames.nextElement();
            String requestParamValue = request.getParameter(requestParamName);
            parametersMap.put(requestParamName, requestParamValue);
        }
        return parametersMap;
    }
    
    /**
     * Loads the request Headers in a map.
     * @param request
     * @return
     */
    private Map<String, String> loadRequestHeadersAsMap(HttpServletRequest request) {
        Map<String, String> headersMap = new HashMap<String, String>();
        Enumeration<?> requestHeaderNames = request.getHeaderNames();
        while (requestHeaderNames.hasMoreElements()) {
            String requestHeaderName = (String) requestHeaderNames.nextElement();
            String requestHeaderValue = request.getHeader(requestHeaderName);
            headersMap.put(requestHeaderName, requestHeaderValue);
        }
        return headersMap;
    }

}
