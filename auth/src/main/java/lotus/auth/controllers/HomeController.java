package lotus.auth.controllers;

import lotus.auth.errors.ResourceNotFoundException;
import lotus.auth.forms.InstallForm;
import lotus.auth.helpers.NginxHelper;
import lotus.auth.models.User;
import lotus.auth.repositiries.UserRepository;
import lotus.auth.service.PolicyService;
import lotus.auth.service.SettingService;
import lotus.auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by flamen on 16-9-18.
 */
@Controller("auth.homeController")
public class HomeController {
    @GetMapping("/info")
    @ResponseBody
    @Cacheable(value = "site.info", key = "#locale.toLanguageTag()")
    public Map<String,Object> getInfo(Locale locale ){
        Map<String,Object> info = new HashMap<>();
        for(String k: new String[]{"title", "subTitle", "keywords", "description", "copyright"}){
            info.put(k, settingService.get(String.format("%s://site/%s", locale.toLanguageTag(), k), String.class));
        }

        Map<String,String> author = new HashMap<>();
        for(String k : new String[]{"name", "email"}){
            author.put(k, settingService.get(String.format("%s://site/author/%s", locale.toLanguageTag(), k), String.class));
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
    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

}
