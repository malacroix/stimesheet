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
import ca.lc.stimesheet.model.User;
import ca.lc.stimesheet.model.event.ErrorCode;
import ca.lc.stimesheet.model.event.Event;
import ca.lc.stimesheet.service.EventServiceImpl;
import ca.lc.stimesheet.service.UserSubscriptionService;
import ca.lc.stimesheet.service.exception.EventHandlingException;

/**
 * @author Marc-Andre Lacroix
 *
 */
@Service
public class SubscribtionCancelEventHandler extends EventTypeHandler {
    private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    @Autowired
    private UserSubscriptionService userSubscriptionService;
    
    public SubscribtionCancelEventHandler() {
        super("SUBSCRIPTION_CANCEL");
    }

    @Override
    public String handleEvent(Event event) throws EventHandlingException {
        log.info("Handling event : " + event.getType());
        
        // First, retrieve the subscription
        String accountId = event.getPayload().getAccount().getAccountIdentifier();
        SubscriptionAccount subsAccount = userSubscriptionService.findSubscriptionAccountById(accountId);
        if (subsAccount == null) {
            throw new EventHandlingException(ErrorCode.ACCOUNT_NOT_FOUND, "Could not retrieve any account '" + accountId + "'.");
        }
        
        // Now, for each assigned user, we must unassign them
        for (User assignedUser : subsAccount.getAssignedUsers()) {
            
            // Do no unassign if it is the creator
            if (!assignedUser.isAccountCreator()) {
                userSubscriptionService.unassignUserFromSubscription(subsAccount, assignedUser);
            }
            
        }
        
        // Finally, we can set the cancel state of the account
        userSubscriptionService.updateSubscriptionAccountStatus(subsAccount, SubscriptionState.CANCELLED);
        
        return null;
    }

}
