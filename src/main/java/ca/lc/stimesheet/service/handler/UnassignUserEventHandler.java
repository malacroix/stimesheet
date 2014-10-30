/**
 * 
 */
package ca.lc.stimesheet.service.handler;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
public class UnassignUserEventHandler extends EventTypeHandler {
    private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    public UnassignUserEventHandler() {
        super("USER_UNASSIGNMENT");
    }

    @Override
    public String handleEvent(Event event) throws EventHandlingException {
        log.info("Handling event : " + event.getType());
        
        // First, retrieve the subscription
        SubscriptionAccount subsAccount = retrieveSubscriptionAccount(event);
        
        User assignedUser = getUserSubscriptionService().findUserByOpenId(event.getPayload().getUser().getOpenId());
        if (assignedUser == null) {
            // User already exists, so cannot subscribe!
            throw new EventHandlingException(ErrorCode.USER_NOT_FOUND, "Could not find the user locally.");
        }
        
        // Also, make sure the user is really in the current subscription account
        if (assignedUser.getAccount() != null && !StringUtils.equals(assignedUser.getAccount().getId(), subsAccount.getId())) {
            // Something is wrong. The user has a different subscription..
            throw new EventHandlingException(ErrorCode.UNKNOWN_ERROR, "Cannot unassign user. He is assigned to another Subscription account.");
        }

        // Unassign it
        getUserSubscriptionService().unassignUserFromSubscription(subsAccount, assignedUser);
        
        return null;
    }

}
