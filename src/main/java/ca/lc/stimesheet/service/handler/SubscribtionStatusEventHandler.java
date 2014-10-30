/**
 * 
 */
package ca.lc.stimesheet.service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.lc.stimesheet.model.SubscriptionAccount;
import ca.lc.stimesheet.model.SubscriptionState;
import ca.lc.stimesheet.model.event.Event;
import ca.lc.stimesheet.service.EventServiceImpl;
import ca.lc.stimesheet.service.exception.EventHandlingException;

/**
 * @author Marc-Andre Lacroix
 *
 */
@Service
public class SubscribtionStatusEventHandler extends EventTypeHandler {
    private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);
    
    @Autowired
    SubscribtionCancelEventHandler subscribtionCancelEventHandler;

    public SubscribtionStatusEventHandler() {
        super("SUBSCRIPTION_STATUS");
    }

    @Override
    public String handleEvent(Event event) throws EventHandlingException {
        log.info("Handling event : " + event.getType());
        
        // First, retrieve the subscription
        SubscriptionAccount subsAccount = retrieveSubscriptionAccount(event);
        
        // Now, handle the notice type
        switch (event.getPayload().getNotice().getType()) {
            case CLOSED:
                // simply call the Cancel event handler which will take care of things
                subscribtionCancelEventHandler.handleEvent(event);
                break;
            case DEACTIVATED:
                // Must suspend the account
                getUserSubscriptionService().updateSubscriptionAccountStatus(subsAccount, SubscriptionState.SUSPENDED);
                break;
            case REACTIVATED:
                // Must reactivate the account
                getUserSubscriptionService().updateSubscriptionAccountStatus(subsAccount, SubscriptionState.ACTIVE);
                break;
            default:
                // Nothing to do for the other cases
        }
        
        return null;
    }

}
