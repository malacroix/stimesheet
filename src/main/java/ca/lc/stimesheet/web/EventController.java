package ca.lc.stimesheet.web;

import java.net.HttpURLConnection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import ca.lc.stimesheet.marshall.ModelMarshaller;
import ca.lc.stimesheet.model.event.Event;
import ca.lc.stimesheet.model.event.EventResult;
import ca.lc.stimesheet.web.oauth.OAuthMarketplaceService;

@RestController
@RequestMapping("/event")
public class EventController {
    private static final Logger log = LoggerFactory.getLogger(EventController.class);
    
    private static String EVENT_FLAG_STATELESS = "STATELESS";
    
    @Autowired
    private OAuthMarketplaceService oAuthMarketplaceService;
    
    @Autowired
    private ModelMarshaller modelMarshaller;

    // TODO : should handle all incoming URLS
    // Create a EventService to handle all the cases, return EventResult
    // EventService factory to retrieve EventHandler

    @RequestMapping(produces=MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody ResponseEntity<EventResult> handle(@RequestParam(value = "type", required = false) String eventType, @RequestParam(value = "url", required = false) String eventUrl, HttpServletRequest request) {
        log.info("Handle Event : type='{}', url='{}'", eventType, eventUrl);
        
        HttpStatus resultStatus = HttpStatus.OK;
        EventResult result = new EventResult();
        
        if (StringUtils.isNotBlank(eventUrl)) {            
            
            // Validate OAuth
            if (oAuthMarketplaceService.validateIncomingMarketplaceRequest("TODO", request)) {
                try {
                    log.debug("Going to retrieve Event from URL '{}'", eventUrl);

                    // Sign the request and get Event Object from XML of provided URL
                    // TODO : better handling of request
                    HttpURLConnection eventRequest = oAuthMarketplaceService.signOutgoingMarketplaceRequest("TODO", eventUrl);
                    Event event = modelMarshaller.unmarshallEvent(eventRequest.getInputStream());
                    
                    log.debug("Event retrieved : {}", event);
                    
                    // Can't do much without an event
                    if (event != null) {
                        // Check if flag is STATELESS (in that case, answer, but do not persist)
                        if (!StringUtils.equalsIgnoreCase(event.getFlag(), EVENT_FLAG_STATELESS)) {
                           // TODO : Handle the event appropriately
                        } else {
                            log.info("Retrieved a Stateless event. Not doing any real processing of the event.");
                        }
                       
                        // If no issue, then its a success
                        result.setSuccess(true);
                    } else {
                        // Can't do much without event
                        result.setSuccess(false);
                        result.setMessage("The retrieved event was null...");
                        result.setErrorCode("RETRIEVED_EVENT_NULL");
                    }

                } catch (RestClientException rce) {
                    String errorMessage = "Could not retrieve the event from received url '" + eventUrl + "': error='" + rce.getMessage() + "'.";
                    log.error(errorMessage, rce);
                    
                    result.setSuccess(false);
                    result.setMessage(errorMessage);
                    result.setErrorCode("CANNOT_RETRIEVE_EVENT");
                } catch (Exception e) {
                    String errorMessage = "There was an issue while tring to process event from '" + eventUrl + "': error='" + e.getMessage() + "'.";
                    log.error(errorMessage, e);
                    
                    // Should preferably handle all the exception cases, but in case we missed something, let's handle it gracefully
                    result.setSuccess(false);
                    result.setMessage(errorMessage);
                    result.setErrorCode("UNKNOWN_ISSUE");
                }
            } else {
                // Incoming Request was not properly signed.
                result.setSuccess(false);
                result.setMessage("The incoming requests was not properly signed.");
                result.setErrorCode("OAUTH_INVALID_REQUEST");
                resultStatus = HttpStatus.UNAUTHORIZED;
            }

        } else {
            // No Event URL, can't do much
            result.setSuccess(false);
            result.setMessage("No Event URL was provided. Cannot process any event.");
            result.setErrorCode("NO_EVENT_URL");
        }

        // Should return an Event Result in all cases
    	return new ResponseEntity<EventResult>(result ,resultStatus);
    }
    
}
