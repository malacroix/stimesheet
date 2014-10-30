/**
 * 
 */
package ca.lc.stimesheet.service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ca.lc.stimesheet.model.SubscriptionAccount;
import ca.lc.stimesheet.model.event.Event;
import ca.lc.stimesheet.service.EventServiceImpl;
import ca.lc.stimesheet.service.exception.EventHandlingException;

/**
 * @author Marc-Andre Lacroix
 *
 */
@Service
public class SubscribtionChangeEventHandler extends EventTypeHandler {
    private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    public SubscribtionChangeEventHandler() {
        super("SUBSCRIPTION_CHANGE");
    }

    @Override
    public String handleEvent(Event event) throws EventHandlingException {
        log.info("Handling event : " + event.getType());
        
        // First, retrieve the subscription
        SubscriptionAccount subsAccount = retrieveSubscriptionAccount(event);
        
        // Then, simply change the Edition code!
        getUserSubscriptionService().updateSubscriptionAccountEditionCode(subsAccount, event.getPayload().getOrder().getEditionCode());
        
        return null;
    }

}
