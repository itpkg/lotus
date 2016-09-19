package lotus.auth.repositiries;

import lotus.auth.models.Setting;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by flamen on 16-9-18.
 */
public interface SettingRepository extends CrudRepository<Setting, Long> {
    Setting findByKey(String key);
}
