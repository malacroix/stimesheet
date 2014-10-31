/**
 * 
 */
package ca.lc.stimesheet.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ca.lc.stimesheet.model.SubscriptionAccount;

/**
 * @author Marc-Andre Lacroix
 *
 */
@Repository
public interface SubscriptionAccountRepository extends SubscriptionAccountCustomRepository, PagingAndSortingRepository<SubscriptionAccount, String> {
      
}
