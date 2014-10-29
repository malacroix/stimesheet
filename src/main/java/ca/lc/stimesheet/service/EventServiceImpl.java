/**
 * 
 */
package ca.lc.stimesheet.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ca.lc.stimesheet.model.event.Event;
import ca.lc.stimesheet.service.exception.EventHandlingException;
import ca.lc.stimesheet.service.handler.EventTypeHandler;

/**
 * @author Marc-Andre Lacroix
 *
 */
@Service
public class EventServiceImpl implements EventService {
    private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    private Map<String, EventTypeHandler> registeredHandlers = new HashMap<String, EventTypeHandler>();
    
    @Override
    public void registerEventTypeHandler(EventTypeHandler eventTypeHandler) {
        
        String eventType = eventTypeHandler.getEventType();
        String normalizedEventType = normalizedEventType(eventType); 
        
        // Make sure it wasn't already registered
        if (!registeredHandlers.containsKey(normalizedEventType)) {
            registeredHandlers.put(normalizedEventType, eventTypeHandler);
        } else {
            throw new IllegalStateException("The EventTypeHandler '" + eventType + "' was already registered.");
        }
    }
    
    @Override
    public void handleEvent(Event event) throws EventHandlingException {

        if (event != null) {
            String normalizedEventType = normalizedEventType(event.getType()); 
            
            EventTypeHandler handler = registeredHandlers.get(normalizedEventType);
            if (handler != null) {
                log.debug("handleEvent: Found EventTypeHandler '" + handler.getEventType() + "'.");
                
                // It should throw an exception if there is any issue
                handler.handleEvent(event);
            } else {
                throw new EventHandlingException("NO_EVENT_HANDLER", "Event '" + event.getType() + "' not handled by the application.");
            }
            
        } else {
            throw new EventHandlingException("NO_EVENT", "There was not Event to handle.");
        }
    }

    /**
     * @param eventTypeHandler
     * @return a normalized EventType 
     */
    private String normalizedEventType(String eventType) {
        // Put in lower case for normalization
        return StringUtils.lowerCase(eventType); 
    }
}
