/**
 * 
 */
package ca.lc.stimesheet.repository;

import org.springframework.stereotype.Repository;

import ca.lc.stimesheet.model.SubscriptionAccount;
import ca.lc.stimesheet.model.User;

/**
 * @author Marc-Andre Lacroix
 *
 */
@Repository
public interface UserCustomRepository  {
    
    /**
     * Assigns the {@link SubscriptionAccount} to the {@link User} 
     * @param userOpenId
     * @param subscriptionAccountId
     */
    void updateUserSubscriptionAccount(String userOpenId, String subscriptionAccountId);
}
