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
import ca.lc.stimesheet.model.event.ErrorCode;
import ca.lc.stimesheet.model.event.Event;
import ca.lc.stimesheet.service.EventServiceImpl;
import ca.lc.stimesheet.service.exception.EventHandlingException;

/**
 * @author Marc-Andre Lacroix
 *
 */
@Service
public class SubscribtionOrderEventHandler extends EventTypeHandler {
    private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);
    
    public SubscribtionOrderEventHandler() {
        super("SUBSCRIPTION_ORDER");
    }

    @Override
    public String handleEvent(Event event) throws EventHandlingException {
        log.info("Handling event : " + event.getType());
        
        // Make sure user does not already have an active subscription
        User userCreator = getUserSubscriptionService().findUserByOpenId(event.getCreator().getOpenId());
        if (userCreator != null && userCreator.isAccountActive()) {
            // User already exists, so cannot subscribe!
            throw new EventHandlingException(ErrorCode.USER_ALREADY_EXISTS, "The user already has an active subscription.");
        }
        
        // So it is all fine, then check Marketplace first
        PartnerMarketplace partnerMartket = getUserSubscriptionService().findPartnerMarketplaceByPartnerId(event.getMarketplace().getPartner());
        if (partnerMartket == null) {
          partnerMartket = getUserSubscriptionService().createPartnerMarketplace(event.getMarketplace());
        }
        
        // Create the Subscription
        SubscriptionAccount subsAccount = getUserSubscriptionService().createSubscriptionAccount(event.getPayload().getOrder());
        
        // Now get the local user
        userCreator = createOrUpdateUser(event.getCreator(), partnerMartket, true);

        // Assign it
        getUserSubscriptionService().assignUserToSubscription(subsAccount, userCreator);
        
        // Now return the newly created ID of the subscription account
        return subsAccount.getId();
    }

}
