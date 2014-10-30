/**
 * 
 */
package ca.lc.stimesheet.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ca.lc.stimesheet.model.User;

/**
 * @author Marc-Andre Lacroix
 *
 */
@Repository
public interface UserRepository extends UserCustomRepository, PagingAndSortingRepository<User, String> {
      
}
