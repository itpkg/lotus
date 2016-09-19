package lotus.auth.repositiries;

import lotus.auth.models.Notice;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by flamen on 16-9-18.
 */
public interface NoticeRepository extends CrudRepository<Notice, Long> {
}
