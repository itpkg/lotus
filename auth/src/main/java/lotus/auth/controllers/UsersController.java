package lotus.auth.controllers;

import lotus.auth.models.User;
import lotus.auth.services.SettingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by flamen on 16-9-18.
 */
@RestController("auth.usersController")
@RequestMapping(path = "/users")
public class UsersController {
    @PostMapping("/sign-in")
    User postSignIn() {
        User user = new User();
        user.setName("test");
        return user;
    }

    @PostMapping("/sign-up")
    void postSignUp() {

    }

    @PostMapping("/confirm")
    void postConfirm() {

    }

    @PostMapping("/unlock")
    void postUnlock() {

    }

    @PostMapping("/forgot-password")
    void postForgotPassword() {

    }

    @PostMapping("/change-password")
    void postChangePassword() {

    }

    @GetMapping("/confirm")
    void getConfirm() {

    }

    @GetMapping("/unlock")
    void getUnlock() {

    }

    @Resource
    SettingService settingService;
}
