/**
 * 
 */
package ca.lc.stimesheet.service;

import ca.lc.stimesheet.model.event.Event;
import ca.lc.stimesheet.service.exception.EventHandlingException;
import ca.lc.stimesheet.service.handler.EventTypeHandler;

/**
 * @author Marc-Andre Lacroix
 *
 */
public interface EventService {

    /**
     * Handles an Event. Internally redirects to a actual EventHandler specific to the event type.
     * @param event
     * @return
     * @throws EventHandlingException if there is an issue processing the event
     */
    void handleEvent(Event event) throws EventHandlingException;
    
    /**
     * Registering method for {@link EventTypeHandler}.
     * It should be called during bean construction.
     * @param eventTypeHandler
     */
    void registerEventTypeHandler(EventTypeHandler eventTypeHandler);
}
