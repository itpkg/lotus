package lotus.auth.repositiries;

import lotus.auth.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by flamen on 16-9-18.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByProviderIdAndProviderType(String providerId, String providerType);
}
