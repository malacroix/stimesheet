/**
 * 
 */
package ca.lc.stimesheet.web.oauth;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthException;
import net.oauth.OAuthMessage;
import net.oauth.OAuthProblemException;
import net.oauth.SimpleOAuthValidator;
import net.oauth.server.OAuthServlet;
import oauth.signpost.basic.DefaultOAuthConsumer;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Marc-Andre Lacroix
 *
 */
@Component
public class OAuthMarketplaceServiceImpl implements OAuthMarketplaceService {
    private static final Logger log = LoggerFactory.getLogger(OAuthMarketplaceServiceImpl.class);

    @Value("${STIMESHEET_CONSUMER_PARTNERS}")
    private String partnerList;
    
    @Value("${STIMESHEET_CONSUMER_KEYS}")
    private String consumerKeyList;
    
    @Value("${STIMESHEET_CONSUMER_SECRETS}")
    private String consumerSecretList;
    
    private Map<String, ConsumerKeySecret> consumerKeySercretByPartner = new HashMap<String, ConsumerKeySecret>();
    
    @PostConstruct
    public void init() {
        // Must construct structure of partner-consumer keys and secret to support multi-partners
        String[] partnerSplit = StringUtils.split(partnerList, ";");
        String[] consumerKeySplit = StringUtils.split(consumerKeyList, ";");
        String[] consumerSecretSplit = StringUtils.split(consumerSecretList, ";");
        
        // loop on the partner
        for (int i = 0; i < partnerSplit.length; i++) {
            String partner = partnerSplit[i];
            String consumerKey = consumerKeySplit[i];
            String consumerSecret = consumerSecretSplit[i];
            
            // Add the partner as lower case (to normalize it)
            consumerKeySercretByPartner.put(StringUtils.lowerCase(partner), new ConsumerKeySecret(consumerKey, consumerSecret));
        }
    }

    @Override
    public boolean validateIncomingMarketplaceRequest(String partnerId, HttpServletRequest incomingRequest) {
        
        // Resolve the ConsumerKeySecret for the provided Partner 
        ConsumerKeySecret consumerKeySecret = consumerKeySercretByPartner.get(StringUtils.lowerCase(partnerId));
        if (consumerKeySecret != null) {
            // Retrieve OAuth info from Incoming request
            String requestedUrl = resolveRequestedUrl(incomingRequest);
            log.debug("Resolved Requested URL : '{}'", requestedUrl);
            
            // Even if requestedUrl is null, the mechanism will try to resolve it, though might not properly work
            OAuthMessage oauthMessage = OAuthServlet.getMessage(incomingRequest, requestedUrl);

            // Construct an accessor and a consumer required to validate the request
            OAuthConsumer consumer = new OAuthConsumer(null, consumerKeySecret.getKey(), consumerKeySecret.getSecret(), null);
            OAuthAccessor accessor = new OAuthAccessor(consumer);

            try {
                
                SimpleOAuthValidator validator = new SimpleOAuthValidator();
                validator.validateMessage(oauthMessage, accessor);
                validator.releaseGarbage();
                
                log.debug("The incoming Request was signed correctly.");
                
                return true;
            } catch (OAuthProblemException e) {
                log.error("OAuth Problem : '" + e.getMessage() + "' - " + e);
                return false;
            } catch (OAuthException | IOException | URISyntaxException e) {
                log.error("Received an invalid request: OAuth validation failed: '{}'", e.getMessage());
                return false;
            }
        } else {
            
            // Could not resolve the partner or it is not supported 
            log.warn("There was no Consumer Key-Secret for Partner '{}'", partnerId);
            return false;
        }

    }

    @Override
    public HttpURLConnection signOutgoingMarketplaceRequest(String partnerId, String outgoingUrlString) throws Exception {
        
        // Resolve the ConsumerKeySecret for the provided Partner 
        ConsumerKeySecret consumerKeySecret = consumerKeySercretByPartner.get(StringUtils.lowerCase(partnerId));
        if (consumerKeySecret != null) {
            oauth.signpost.OAuthConsumer consumer = new DefaultOAuthConsumer(consumerKeySecret.getKey(), consumerKeySecret.getSecret());
            
            // TODO : enhanced connection request validation
            
            URL outgoingUrl = new URL(outgoingUrlString);
            HttpURLConnection request = (HttpURLConnection) outgoingUrl.openConnection();
            consumer.sign(request);
            request.connect();
            
            return request;
        } else {
            throw new IllegalStateException("There was no Consumer Key-Secret for Partner '" + partnerId + "'");
        }
    }
    
    /**
     * Resolve the actual URL that was requested to make sure that the OAuth Verification is done properly.
     * @param request HttpServletRequest
     * @return the requested URL or null if there was an issue
     */
    private static String resolveRequestedUrl(HttpServletRequest request) {
        
        try {
            // Heroku does some load balancing and reroutes internally from the HTTPS to HTTP protocol, so always validate this.
            URIBuilder uriBuilder = new URIBuilder(request.getRequestURL().toString());
            uriBuilder.setQuery(request.getQueryString());

            // Now validate the forwarding header Heroku sets when converting from HTTPS to HTTP
            String forwardedProtocol = request.getHeader("x-forwarded-proto");
            
            if (StringUtils.isNoneBlank(forwardedProtocol)) {
                // Set the forwardedProtocol as a precaution
                uriBuilder.setScheme(forwardedProtocol);
            }
            
            return uriBuilder.toString();
        } catch (URISyntaxException use) {
            log.error("Could not properly resolve the requested URL", use);
            return null;
        }
    }
    
    private static class ConsumerKeySecret {
        private String key;
        private String secret;
        
        public ConsumerKeySecret(String key, String secret) {
            this.key = key;
            this.secret = secret;
        }
        
        /**
         * @return the key
         */
        public String getKey() {
            return key;
        }
        
        /**
         * @return the secret
         */
        public String getSecret() {
            return secret;
        }
    }
}
