package lotus.auth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by flamen on 16-9-18.
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/install", method = RequestMethod.GET)
    public String getInstall( Model model) {
        model.addAttribute("name", "haha");
        return "install";
    }

    @RequestMapping(value = "/install", method = RequestMethod.POST)
    public String postInstall( Model model) {
        model.addAttribute("name", "haha");
        return "home";
    }
}
