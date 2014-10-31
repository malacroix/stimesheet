/**
 * 
 */
package ca.lc.stimesheet.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import ca.lc.stimesheet.model.PartnerMarketplace;

/**
 * @author Marc-Andre Lacroix
 *
 */
public class PartnetMarketplaceRepositoryImpl implements PartnetMarketplaceCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Override
    public List<PartnerMarketplace> findAllMarketplaces() {
        return mongoTemplate.findAll(PartnerMarketplace.class);
    }
}
