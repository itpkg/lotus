package lotus.auth.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * Created by flamen on 16-9-18.
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/install", method = RequestMethod.GET)
    public String getInstall(Locale locale,  Model model) {
        logger.debug("### "+locale.toLanguageTag()+" "+messageSource.getMessage("buttons.submit", null, locale));
        model.addAttribute("name", "haha");
        return "install";
    }

    @RequestMapping(value = "/install", method = RequestMethod.POST)
    public String postInstall( Model model) {
        model.addAttribute("name", "haha");
        return "home";
    }

    @Resource
    private MessageSource messageSource;
    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

}
