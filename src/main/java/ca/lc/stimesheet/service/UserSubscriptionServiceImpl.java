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

    @Override
    public User findUserByOpenId(String openId) {
        return userRepository.findOne(openId);
    }

    @Override
    public User createUser(EventUser eventUser, PartnerMarketplace marketplace) {
        User newUser = new User();
        newUser.setOpenId(eventUser.getOpenId());
        newUser.setUuid(eventUser.getUuid());
        newUser.setFirstName(eventUser.getFirstName());
        newUser.setLastName(eventUser.getLastName());
        newUser.setLanguage(eventUser.getLanguage());
        newUser.setFromMarketplace(marketplace);

        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(User user) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public PartnerMarketplace findPartnerMarketplaceByPartnerId(String partnerId) {
        return partnerMarketplaceRepository.findOne(partnerId);
    }

    @Override
    public PartnerMarketplace createPartnerMarketplace(Marketplace marketplace) {
        PartnerMarketplace newPartnerMarket = new PartnerMarketplace();
        newPartnerMarket.setPartnerId(marketplace.getPartner());
        newPartnerMarket.setBaseUrl(marketplace.getBaseUrl());

        return partnerMarketplaceRepository.save(newPartnerMarket);
    }

    /* (non-Javadoc)
     * @see ca.lc.stimesheet.service.UserSubscriptionService#findSubscriptionAccountById(java.lang.String)
     */
    @Override
    public SubscriptionAccount findSubscriptionAccountById(String accountId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SubscriptionAccount createSubscriptionAccount(Order order) {
        SubscriptionAccount newSubsAccount = new SubscriptionAccount();
        newSubsAccount.setEditionCode(order.getEditionCode());
        
        // We do not know if it is a trial, so it is active by default
        newSubsAccount.setState(SubscriptionState.ACTIVE);

        return subscriptionAccountRepository.save(newSubsAccount);
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
