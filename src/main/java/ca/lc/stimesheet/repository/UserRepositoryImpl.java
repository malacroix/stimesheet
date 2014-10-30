/**
 * 
 */
package ca.lc.stimesheet.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import ca.lc.stimesheet.model.User;

/**
 * @author Marc-Andre Lacroix
 *
 */
public class UserRepositoryImpl implements UserCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Override
    public void updateUserSubscriptionAccount(String userOpenId, String subscriptionAccountId) {
        Query query = Query.query(Criteria.where("_id").is(userOpenId));
        Update update = Update.update("account", subscriptionAccountId);
        
        mongoTemplate.updateFirst(query, update, User.class);
    }
}
