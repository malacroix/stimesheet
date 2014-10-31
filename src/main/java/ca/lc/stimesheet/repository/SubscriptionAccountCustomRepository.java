/**
 * 
 */
package ca.lc.stimesheet.repository;

import java.util.List;

import ca.lc.stimesheet.model.SubscriptionAccount;
import ca.lc.stimesheet.model.SubscriptionState;
import ca.lc.stimesheet.model.User;


/**
 * @author Marc-Andre Lacroix
 *
 */
public interface SubscriptionAccountCustomRepository {
    
    /**
     * Adds the {@link User} to Assigned Users list of the {@link SubscriptionAccount}
     * @param subscriptionAccountId
     * @param userOpenId
     */
    void assignUser(String subscriptionAccountId, String userOpenId);
    
    /**
     * Removes the {@link User} from Assigned Users list of the {@link SubscriptionAccount}
     * @param subscriptionAccountId
     * @param userOpenId
     */
    void unassignUser(String subscriptionAccountId, String userOpenId);
    
    /**
     * Updates the {@link SubscriptionState} of the {@link SubscriptionAccount}
     * @param subscriptionAccountId
     * @param newState
     */
    void updateSubscriptionAccountStatus(String subscriptionAccountId, SubscriptionState newState);
    
    /**
     * Updates the EditionCode of the {@link SubscriptionAccount}
     * @param subscriptionAccountId
     * @param newEditionCode
     */
    void updateSubscriptionAccountEditionCode(String subscriptionAccountId, String newEditionCode);
    
    /**
     * @return all the {@link SubscriptionAccount}.
     */
    List<SubscriptionAccount> findAllSubscriptionAccounts();
}
