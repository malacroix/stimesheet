/**
 * 
 */
package ca.lc.stimesheet.service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.lc.stimesheet.model.PartnerMarketplace;
import ca.lc.stimesheet.model.SubscriptionAccount;
import ca.lc.stimesheet.model.User;
import ca.lc.stimesheet.model.event.ErrorCode;
import ca.lc.stimesheet.model.event.Event;
import ca.lc.stimesheet.model.event.EventUser;
import ca.lc.stimesheet.service.EventServiceImpl;
import ca.lc.stimesheet.service.UserSubscriptionService;
import ca.lc.stimesheet.service.exception.EventHandlingException;

/**
 * @author Marc-Andre Lacroix
 *
 */
@Service
public class SubscribtionOrderEventHandler extends EventTypeHandler {
    private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);
    
    @Autowired
    private UserSubscriptionService userSubscriptionService;

    public SubscribtionOrderEventHandler() {
        super("SUBSCRIPTION_ORDER");
    }

    @Override
    public String handleEvent(Event event) throws EventHandlingException {
        log.info("Handling event : " + event.getType());
        
        // Make sure user does not already have an active subscription
        User userCreator = userSubscriptionService.findUserByOpenId(event.getCreator().getOpenId());
        if (userCreator != null && userCreator.isAccountActive()) {
            // User already exists, so cannot subscribe!
            throw new EventHandlingException(ErrorCode.USER_ALREADY_EXISTS, "The user already has an active subscription.");
        }
        
        // So it is all fine, then check Marketplace first
        PartnerMarketplace partnerMartket = userSubscriptionService.findPartnerMarketplaceByPartnerId(event.getMarketplace().getPartner());
        if (partnerMartket == null) {
          partnerMartket = userSubscriptionService.createPartnerMarketplace(event.getMarketplace());
        }
        
        // Create the Subscription
        SubscriptionAccount subsAccount = userSubscriptionService.createSubscriptionAccount(event.getPayload().getOrder());
        
        if (userCreator != null) {
            // User already exists, so simply update it
            EventUser eventUser = event.getCreator();
            userCreator.setFirstName(eventUser.getFirstName());
            userCreator.setLastName(eventUser.getLastName());
            userCreator.setLanguage(eventUser.getLanguage());
            userCreator.setAccountCreator(true);
            userCreator = userSubscriptionService.updateUser(userCreator);
        } else {
            // New user, so create it
            userCreator = userSubscriptionService.createUser(event.getCreator(), partnerMartket, true);
        }
        
        // Assign it
        userSubscriptionService.assignUserToSubscription(subsAccount, userCreator);
        
        // Now return the newly created ID of the subscription account
        return subsAccount.getId();
    }

}
