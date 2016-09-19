package lotus.auth.services;

import lotus.auth.models.Locale;
import lotus.auth.repositiries.LocaleRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by flamen on 16-9-19.
 */
@Component("auth.localeService")
public class I18nService {
    public void set(java.util.Locale locale, String code, String body){
        Locale l = localeRepository.findByCodeAndLang(code, locale.toLanguageTag());
        if(l == null){
            l = new Locale();
            l.setCode(code);
            l.setLang(locale.toLanguageTag());
        }
        l.setBody(body);
        localeRepository.save(l);
    }

    public String t(java.util.Locale locale, String code, Object...args){
        Locale l = localeRepository.findByCodeAndLang(code, locale.toLanguageTag());
        return l == null ? code : String.format(l.getBody(), args);
    }
    @Resource
    LocaleRepository localeRepository;
}
