/**
 * 
 */
package ca.lc.stimesheet.repository;

import java.util.List;

import ca.lc.stimesheet.model.PartnerMarketplace;


/**
 * @author Marc-Andre Lacroix
 *
 */
public interface PartnerMarketplaceCustomRepository {
    
    /**
     * @return All known {@link PartnerMarketplace}
     */
    List<PartnerMarketplace> findAllMarketplaces();
}
