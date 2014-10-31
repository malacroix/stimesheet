/**
 * 
 */
package ca.lc.stimesheet.service;

import java.util.List;

import ca.lc.stimesheet.model.PartnerMarketplace;
import ca.lc.stimesheet.model.SubscriptionAccount;
import ca.lc.stimesheet.model.SubscriptionState;
import ca.lc.stimesheet.model.User;
import ca.lc.stimesheet.model.event.EventUser;
import ca.lc.stimesheet.model.event.Marketplace;
import ca.lc.stimesheet.model.event.Order;

/**
 * @author Marc-Andre Lacroix
 *
 */
public interface UserSubscriptionService {

    // ********************************************************
    // User operations
    
    /**
     * Finds an existing User by its openID.
     * @param openId
     * @return
     */
    User findUserByOpenId(String openId);
    
    /**
     * Creates a new {@link User}.
     * @param eventUser
     * @param marketplace
     * @param accountCreator
     * @return
     */
    User createUser(EventUser eventUser, PartnerMarketplace marketplace, boolean accountCreator);
    
    /**
     * Updates a {@link User} infos.
     * @param user
     * @return
     */
    User updateUser(User user);
    
    // ********************************************************
    // Marketplace operations
    
    /**
     * Finds an existing {@link PartnerMarketplace}.
     * @param partnerId
     * @return
     */
    PartnerMarketplace findPartnerMarketplaceByPartnerId(String partnerId);
    
    /**
     * Create a new {@link PartnerMarketplace}
     * @param marketplace
     * @return
     */
    PartnerMarketplace createPartnerMarketplace(Marketplace marketplace);
    
    /**
     * @return all the known {@link PartnerMarketplace}
     */
    List<PartnerMarketplace> retrieveAllPartnerMarketplaces();
    
    // ********************************************************
    // SubscriptionAccounts operations
    
    /**
     * Finds an existing {@link SubscriptionAccount}.
     * @param accountId
     * @return
     */
    SubscriptionAccount findSubscriptionAccountById(String accountId);
    
    /**
     * Creates a new {@link SubscriptionAccount}.
     * @param order
     * @return
     */
    SubscriptionAccount createSubscriptionAccount(Order order);
    
    /**
     * Adds the {@link User} to Assigned Users list of the {@link SubscriptionAccount}
     * @param account
     * @param user
     */
    void assignUserToSubscription(SubscriptionAccount account, User user);
    
    /**
     * Removes the {@link User} from Assigned Users list of the {@link SubscriptionAccount}
     * @param account
     * @param user
     */
    void unassignUserFromSubscription(SubscriptionAccount account, User user);
    
    /**
     * Updates the {@link SubscriptionState} of the {@link SubscriptionAccount}
     * @param account
     * @param newState
     */
    void updateSubscriptionAccountStatus(SubscriptionAccount account, SubscriptionState newState);
    
    /**
     * Updates the EditionCode of the {@link SubscriptionAccount}
     * @param account
     * @param newEditionCode
     */
    void updateSubscriptionAccountEditionCode(SubscriptionAccount account, String newEditionCode);
    
    /**
     * @return All the {@link SubscriptionAccount}
     */
    List<SubscriptionAccount> findAllSubscriptionAccounts();
}
