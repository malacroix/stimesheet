/**
 * 
 */
package ca.lc.stimesheet.service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ca.lc.stimesheet.model.PartnerMarketplace;
import ca.lc.stimesheet.model.SubscriptionAccount;
import ca.lc.stimesheet.model.User;
import ca.lc.stimesheet.model.event.Event;
import ca.lc.stimesheet.service.EventServiceImpl;
import ca.lc.stimesheet.service.exception.EventHandlingException;

/**
 * @author Marc-Andre Lacroix
 *
 */
@Service
public class AssignUserEventHandler extends EventTypeHandler {
    private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    public AssignUserEventHandler() {
        super("USER_ASSIGNMENT");
    }

    @Override
    public String handleEvent(Event event) throws EventHandlingException {
        log.info("Handling event : " + event.getType());
        
        // First, retrieve the subscription
        SubscriptionAccount subsAccount = retrieveSubscriptionAccount(event);

        // Now check if user already exists or create it if not
        PartnerMarketplace partnerMartket = getUserSubscriptionService().findPartnerMarketplaceByPartnerId(event.getMarketplace().getPartner());
        User assignUser = createOrUpdateUser(event.getPayload().getUser(), partnerMartket, false);

        // Assign it
        getUserSubscriptionService().assignUserToSubscription(subsAccount, assignUser);
        
        return null;
    }

}
