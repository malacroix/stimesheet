package ca.lc.stimesheet.web;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import ca.lc.stimesheet.model.event.Event;
import ca.lc.stimesheet.model.event.EventResult;

@RestController
@RequestMapping("/event")
public class EventController {
    private static final Logger log = LoggerFactory.getLogger(EventController.class);
    
    private static String EVENT_FLAG_STATELESS = "STATELESS";
    
    private RestTemplate restTemplate;

    // TODO : should handle all incoming URLS
    // Create a EventService to handle all the cases, return EventResult
    // EventService factory to retrieve EventHandler
    
    @PostConstruct
    public void init() {
        // TODO : convert to OAuthRestTemplate
        restTemplate = new RestTemplate();
    }

    @RequestMapping(produces=MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody EventResult handle(@RequestParam(value = "type", required = false) String eventType, @RequestParam(value = "url", required = false) String eventUrl) {
        log.info("Handle Event : type='{}', url='{}'", eventType, eventUrl);
        
        EventResult result = new EventResult();
        
        if (StringUtils.isNotBlank(eventUrl)) {            
            // TODO : Validate OAuth

            try {
                // Get Event Object from XML of provided URL
                log.debug("Going to retrieve Event from URL '{}'", eventUrl);
                Event event = restTemplate.getForObject(eventUrl, Event.class);
                
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
            // No Event URL, can't do much
            result.setSuccess(false);
            result.setMessage("No Event URL was provided. Cannot process any event.");
            result.setErrorCode("NO_EVENT_URL");
        }

        // Should return an Event Result in all cases
    	return result;
    }
    
}
