package lotus.auth.helpers;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import lotus.auth.services.SettingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by flamen on 16-9-19.
 */
@Component("auth.nginxHelper")
public class NginxHelper {
    class Model {
        String domain;
        boolean https;
        int port;
    }

    public void conf(Writer writer) throws IOException {
        Model model = new Model();
        model.domain = settingService.get("site.domain", String.class);
        model.https = settingService.get("site.https?", Boolean.class);
        model.port = port;

        Mustache mustache = factory.compile("nginx.conf");
        mustache.execute(writer, model).flush();
    }

    @PostConstruct
    void init() {
        factory = new DefaultMustacheFactory();
    }

    @Resource
    SettingService settingService;

    @Value("${server.port}")
    int port;

    private MustacheFactory factory;

}
