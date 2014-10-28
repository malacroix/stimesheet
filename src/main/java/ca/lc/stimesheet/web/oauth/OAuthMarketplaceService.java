/**
 * 
 */
package ca.lc.stimesheet.web.oauth;

import java.net.HttpURLConnection;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marc-Andre Lacroix
 *
 */
public interface OAuthMarketplaceService {

    boolean validateIncomingMarketplaceRequest(String partnerId, HttpServletRequest incomingRequest);
    
    HttpURLConnection signOutgoingMarketplaceRequest(String partnerId, String outgoingUrlString) throws Exception;
}
