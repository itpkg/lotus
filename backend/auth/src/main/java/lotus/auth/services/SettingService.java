package lotus.auth.services;

import com.google.gson.Gson;
import lotus.auth.helpers.EncryptHelper;
import lotus.auth.models.Setting;
import lotus.auth.repositiries.SettingRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by flamen on 16-9-18.
 */
@Service("auth.settingService")
public class SettingService {
    public <T> void set(String key, T obj) {
        set(key, obj, false);
    }

    public <T> void set(String key, T obj, boolean encode) {
        Setting s = settingRepository.findByKey(key);
        if (s == null) {
            s = new Setting();
            s.setKey(key);
        }
        s.setEncode(encode);

        String val = gson.toJson(obj);
        if (encode) {
            val = encryptHelper.encode(val);
        }
        s.setVal(val);
        settingRepository.save(s);
    }

    public <T> T get(String key, Class<T> clazz) {
        Setting s = settingRepository.findByKey(key);
        if (s == null) {
            return null;
        }
        String val = s.getVal();
        if (s.isEncode()) {
            val = encryptHelper.decode(val);
        }
        return gson.fromJson(val, clazz);
    }

    @PostConstruct
    void init() {
        gson = new Gson();
    }

    private Gson gson;
    @Resource
    EncryptHelper encryptHelper;
    @Resource
    SettingRepository settingRepository;


}
