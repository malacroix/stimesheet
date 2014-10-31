/**
 * 
 */
package ca.lc.stimesheet.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import ca.lc.stimesheet.model.SubscriptionAccount;
import ca.lc.stimesheet.model.SubscriptionState;

/**
 * @author Marc-Andre Lacroix
 *
 */
public class SubscriptionAccountRepositoryImpl implements SubscriptionAccountCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Override
    public void assignUser(String subscriptionAccountId, String userOpenId) {
        Query query = Query.query(Criteria.where("_id").is(subscriptionAccountId));
        Update update = new Update().push("assignedUsers", userOpenId);
        
        mongoTemplate.updateFirst(query, update, SubscriptionAccount.class);
    }

    @Override
    public void unassignUser(String subscriptionAccountId, String userOpenId) {
        Query query = Query.query(Criteria.where("_id").is(subscriptionAccountId));
        Update update = new Update().pull("assignedUsers", userOpenId);
        
        mongoTemplate.updateFirst(query, update, SubscriptionAccount.class);
    }

    @Override
    public void updateSubscriptionAccountStatus(String subscriptionAccountId, SubscriptionState newState) {
        Query query = Query.query(Criteria.where("_id").is(subscriptionAccountId));
        Update update = Update.update("state", newState);
        
        mongoTemplate.updateFirst(query, update, SubscriptionAccount.class);
    }

    @Override
    public void updateSubscriptionAccountEditionCode(String subscriptionAccountId, String newEditionCode) {
        Query query = Query.query(Criteria.where("_id").is(subscriptionAccountId));
        Update update = Update.update("editionCode", newEditionCode);
        
        mongoTemplate.updateFirst(query, update, SubscriptionAccount.class);
    }

}
