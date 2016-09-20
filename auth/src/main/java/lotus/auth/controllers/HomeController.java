package lotus.auth.controllers;

import lotus.auth.errors.ResourceNotFoundException;
import lotus.auth.forms.InstallForm;
import lotus.auth.helpers.NginxHelper;
import lotus.auth.models.User;
import lotus.auth.repositiries.UserRepository;
import lotus.auth.services.I18nService;
import lotus.auth.services.PolicyService;
import lotus.auth.services.SettingService;
import lotus.auth.services.UserService;
import org.hibernate.engine.spi.SessionImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by flamen on 16-9-18.
 */
@Controller("auth.homeController")
public class HomeController {
    public class Route implements Serializable{
        private String method;
        private String pattern;

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getPattern() {
            return pattern;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }
    }

    private List<Route> _getRoutes(){
        List<Route> routes = new ArrayList<>();
        for(RequestMappingInfo rmi:this.handlerMapping.getHandlerMethods().keySet()){
            for(RequestMethod rm:rmi.getMethodsCondition().getMethods()){
                rmi.getPatternsCondition().getPatterns().forEach((pat)->{
                    Route r = new Route();
                    r.method = rm.name();
                    r.pattern = pat;
                    routes.add(r);
                });
            }
        }
        return routes;
    }
    private Map<String,Object> _getDatabase() throws SQLException{
        SessionImplementor si =                (SessionImplementor) entityManager.getDelegate();
        DatabaseMetaData dmd = si.connection().getMetaData();
        Map<String, Object> map = new HashMap<>();

        Map<String, String> driver = new HashMap<>();
        driver.put("name", dmd.getDriverName());
        driver.put("version",dmd.getDriverVersion());
        map.put("driver", driver);

        Map<String, String> product = new HashMap<>();
        product.put("name",dmd.getDatabaseProductName());
        product.put("version", dmd.getDatabaseProductVersion());
        map.put("product", product);

        map.put("user", dmd.getUserName());
        map.put("url",  dmd.getURL());
        map.put("keywords", dmd.getSQLKeywords());
        return map;
    }
    @GetMapping("/status")
    @ResponseBody
    public Map<String, Object> getStatus() throws SQLException{
        Map<String, Object> map = new HashMap<>();
        map.put("routes", this._getRoutes());
        map.put("database", this._getDatabase());

        return map;
    }
    @GetMapping("/info")
    @ResponseBody
    @Cacheable(value = "site.info", key = "#locale.toLanguageTag()")
    public Map<String,Object> getInfo(Locale locale ){
        Map<String,Object> info = new HashMap<>();
        for(String k: new String[]{"title", "subTitle", "keywords", "description", "copyright"}){
            info.put(k, i18nService.t(locale, String.format("site.%s", k)));
        }

        Map<String,String> author = new HashMap<>();
        for(String k : new String[]{"name", "email"}){
            author.put(k, i18nService.t(locale, String.format("site.author.%s", k)));
        }
        info.put("author", author);

        info.put("lang", locale.toLanguageTag());
        return info;
    }

    @GetMapping("/install")
    String getInstall() {
        if(userRepository.count() >0){
            throw new ResourceNotFoundException();
        }
        return "install";
    }


    @PostMapping("/install")
    @ResponseBody
    User postInstall(@Valid InstallForm form, HttpServletResponse response) throws NoSuchAlgorithmException, IOException {
        if(userRepository.count() >0){
            throw new ResourceNotFoundException();
        }
        settingService.set("site.domain", form.getDomain());
        settingService.set("site.https?", form.isHttps());
        User user = userService.add(form.getEmail(), form.getUsername(), form.getPassword());
        policyService.allow(user.getId(), "admin");
        policyService.allow(user.getId(), "root");
        return user;
    }

    @GetMapping("/nginx.conf")
    void getNginxConf(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        nginxHelper.conf(response.getWriter());
    }


    @Resource
    MessageSource messageSource;
    @Resource
    NginxHelper nginxHelper;
    @Resource
    SettingService settingService;
    @Resource
    UserService userService;
    @Resource
    UserRepository userRepository;
    @Resource
    PolicyService policyService;
    @Resource
    I18nService i18nService;
    @Resource
    RequestMappingHandlerMapping handlerMapping;


    @Resource
    EntityManager entityManager;
    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

}
