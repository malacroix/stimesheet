/**
 * 
 */
package ca.lc.stimesheet.service.handler;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import ca.lc.stimesheet.model.event.Event;
import ca.lc.stimesheet.model.event.EventResult;
import ca.lc.stimesheet.service.EventService;
import ca.lc.stimesheet.service.exception.EventHandlingException;

/**
 * @author Marc-Andre Lacroix
 *
 */
public abstract class EventTypeHandler {
    
    @Autowired
    private EventService eventService;
    
    private String eventType;

    public EventTypeHandler(String eventType) {
        this.eventType = eventType;
    }
    
    @PostConstruct
    private void init() {
        // Must register automatically to the EventService
        eventService.registerEventTypeHandler(this);
    }
    
    /**
     * @return The type of event it handles.
     */
    public String getEventType() {
        return eventType;
    }
    
    /**
     * Handles an event.
     * @param event
     * @return The {@link EventResult}
     */
    public abstract void handleEvent(Event event) throws EventHandlingException;
}
