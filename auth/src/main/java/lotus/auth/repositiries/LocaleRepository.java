package lotus.auth.repositiries;

import lotus.auth.models.Locale;
import lotus.auth.models.Policy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by flamen on 16-9-19.
 */
public interface LocaleRepository extends CrudRepository<Locale, Long> {
    Locale findByCodeAndLang(String code, String lang);
    List<Locale> findByLang(String lang);
}
