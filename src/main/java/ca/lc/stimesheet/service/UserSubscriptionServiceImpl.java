/**
 * 
 */
package ca.lc.stimesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.lc.stimesheet.model.PartnerMarketplace;
import ca.lc.stimesheet.model.SubscriptionAccount;
import ca.lc.stimesheet.model.SubscriptionState;
import ca.lc.stimesheet.model.User;
import ca.lc.stimesheet.model.event.EventUser;
import ca.lc.stimesheet.model.event.Marketplace;
import ca.lc.stimesheet.model.event.Order;
import ca.lc.stimesheet.repository.PartnerMarketplaceRepository;
import ca.lc.stimesheet.repository.SubscriptionAccountRepository;
import ca.lc.stimesheet.repository.UserRepository;

/**
 * @author Marc-Andre Lacroix
 *
 */
@Service
public class UserSubscriptionServiceImpl implements UserSubscriptionService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PartnerMarketplaceRepository partnerMarketplaceRepository;
    
    @Autowired
    private SubscriptionAccountRepository subscriptionAccountRepository;

    /* (non-Javadoc)
     * @see ca.lc.stimesheet.service.UserSubscriptionService#findUserByOpenId(java.lang.String)
     */
    @Override
    public User findUserByOpenId(String openId) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see ca.lc.stimesheet.service.UserSubscriptionService#createUser(ca.lc.stimesheet.model.event.EventUser, ca.lc.stimesheet.model.PartnerMarketplace)
     */
    @Override
    public User createUser(EventUser eventUser, PartnerMarketplace marketplace) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see ca.lc.stimesheet.service.UserSubscriptionService#updateUser(ca.lc.stimesheet.model.User)
     */
    @Override
    public User updateUser(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see ca.lc.stimesheet.service.UserSubscriptionService#findPartnerMarketplaceByPartnerId(java.lang.String)
     */
    @Override
    public PartnerMarketplace findPartnerMarketplaceByPartnerId(String partnerId) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see ca.lc.stimesheet.service.UserSubscriptionService#createPartnerMarketplace(ca.lc.stimesheet.model.event.Marketplace)
     */
    @Override
    public PartnerMarketplace createPartnerMarketplace(Marketplace marketplace) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see ca.lc.stimesheet.service.UserSubscriptionService#findSubscriptionAccountById(java.lang.String)
     */
    @Override
    public SubscriptionAccount findSubscriptionAccountById(String accountId) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see ca.lc.stimesheet.service.UserSubscriptionService#createSubscriptionAccount(ca.lc.stimesheet.model.event.Order)
     */
    @Override
    public SubscriptionAccount createSubscriptionAccount(Order order) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see ca.lc.stimesheet.service.UserSubscriptionService#assignUserToSubscription(ca.lc.stimesheet.model.SubscriptionAccount, ca.lc.stimesheet.model.User)
     */
    @Override
    public void assignUserToSubscription(SubscriptionAccount account, User user) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see ca.lc.stimesheet.service.UserSubscriptionService#unassignUserFromSubscription(ca.lc.stimesheet.model.SubscriptionAccount, ca.lc.stimesheet.model.User)
     */
    @Override
    public void unassignUserFromSubscription(SubscriptionAccount account, User user) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see ca.lc.stimesheet.service.UserSubscriptionService#updateSubscriptionAccountStatus(ca.lc.stimesheet.model.SubscriptionAccount, ca.lc.stimesheet.model.SubscriptionState)
     */
    @Override
    public void updateSubscriptionAccountStatus(SubscriptionAccount account, SubscriptionState newState) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see ca.lc.stimesheet.service.UserSubscriptionService#updateSubscriptionAccountEditionCode(ca.lc.stimesheet.model.SubscriptionAccount, java.lang.String)
     */
    @Override
    public void updateSubscriptionAccountEditionCode(SubscriptionAccount account, String newEditionCode) {
        // TODO Auto-generated method stub
        
    }


}
