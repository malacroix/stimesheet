/**
 * 
 */
package ca.lc.stimesheet.service.handler;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import ca.lc.stimesheet.model.PartnerMarketplace;
import ca.lc.stimesheet.model.SubscriptionAccount;
import ca.lc.stimesheet.model.User;
import ca.lc.stimesheet.model.event.ErrorCode;
import ca.lc.stimesheet.model.event.Event;
import ca.lc.stimesheet.model.event.EventUser;
import ca.lc.stimesheet.service.EventService;
import ca.lc.stimesheet.service.UserSubscriptionService;
import ca.lc.stimesheet.service.exception.EventHandlingException;

/**
 * @author Marc-Andre Lacroix
 *
 */
public abstract class EventTypeHandler {
    
    @Autowired
    private EventService eventService;
    
    @Autowired
    private UserSubscriptionService userSubscriptionService;
    
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
     * @return The Account ID of a new Subscription Account, otherwise null
     */
    public abstract String handleEvent(Event event) throws EventHandlingException;
    
    /**
     * @return the userSubscriptionService
     */
    public UserSubscriptionService getUserSubscriptionService() {
        return userSubscriptionService;
    }
    
    /**
     * Retrieve an existing {@link SubscriptionAccount} by its ID or throws an ACCOUNT_NOT_FOUND exception
     * if it cannot find it. 
     * @param event
     * @return
     * @throws EventHandlingException
     */
    protected SubscriptionAccount retrieveSubscriptionAccount(Event event) throws EventHandlingException {
        String accountId = event.getPayload().getAccount().getAccountIdentifier();
        SubscriptionAccount subsAccount = getUserSubscriptionService().findSubscriptionAccountById(accountId);
        if (subsAccount != null) {
            return subsAccount;
        } else {
            throw new EventHandlingException(ErrorCode.ACCOUNT_NOT_FOUND, "Could not retrieve any account '" + accountId + "'.");
        }
    }
    
    /**
     * Based on the user info retrieved from the Event, it will create it locally if it doesn't exist
     * or it will update it.
     * @param eventUser
     * @param partnerMarketplace
     * @param accountCreator
     * @return
     */
    protected User createOrUpdateUser(EventUser eventUser, PartnerMarketplace partnerMarketplace, boolean accountCreator) {
        User user = getUserSubscriptionService().findUserByOpenId(eventUser.getOpenId());
        if (user != null) {
            // User already exists, so simply update it
            user.setFirstName(eventUser.getFirstName());
            user.setLastName(eventUser.getLastName());
            user.setEmail(eventUser.getEmail());
            user.setLanguage(eventUser.getLanguage());
            user.setAccountCreator(accountCreator);
            user = getUserSubscriptionService().updateUser(user);
        } else {
            // New user, so create it
            user = getUserSubscriptionService().createUser(eventUser, partnerMarketplace, accountCreator);
        }
        
        return user;
    }
}
