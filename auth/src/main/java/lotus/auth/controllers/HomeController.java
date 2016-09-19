package lotus.auth.controllers;

import lotus.auth.forms.InstallForm;
import lotus.auth.helpers.NginxHelper;
import lotus.auth.models.User;
import lotus.auth.repositiries.UserRepository;
import lotus.auth.service.PolicyService;
import lotus.auth.service.SettingService;
import lotus.auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by flamen on 16-9-18.
 */
@Controller("auth.homeController")
public class HomeController {
    @GetMapping("/install")
    public String getInstall() {
        return "install";
    }


    @PostMapping("/install")
    @ResponseBody
    public User postInstall(@Valid InstallForm form) throws NoSuchAlgorithmException, IOException {
        settingService.set("site.domain", form.getDomain());
        settingService.set("site.https?", form.isHttps());
        User user = userService.add(form.getEmail(), form.getUsername(), form.getPassword());
        policyService.allow(user.getId(), "admin");
        policyService.allow(user.getId(), "root");
        return user;
    }

    @GetMapping("/nginx.conf")
    public void getNginxConf(HttpServletResponse response) throws IOException {
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
    PolicyService policyService;
    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

}
