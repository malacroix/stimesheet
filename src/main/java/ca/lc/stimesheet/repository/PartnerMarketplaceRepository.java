/**
 * 
 */
package ca.lc.stimesheet.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ca.lc.stimesheet.model.PartnerMarketplace;

/**
 * @author Marc-Andre Lacroix
 *
 */
@Repository
public interface PartnerMarketplaceRepository extends PartnerMarketplaceCustomRepository,PagingAndSortingRepository<PartnerMarketplace, String> {
      
}
