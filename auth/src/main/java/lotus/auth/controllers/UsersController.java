package lotus.auth.controllers;

import lotus.auth.forms.SignInForm;
import lotus.auth.helpers.EncryptHelper;
import lotus.auth.models.User;
import lotus.auth.repositiries.UserRepository;
import lotus.auth.services.SettingService;
import lotus.auth.services.UserService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by flamen on 16-9-18.
 */
@RestController("auth.usersController")
@RequestMapping(path = "/users")
public class UsersController {
    @PostMapping("/sign-in")
    User postSignIn(Locale locale, @Valid SignInForm form) {

        User user = userService.getUserByEmail(form.getEmail(), locale);
        if (!encryptHelper.check(form.getPassword(), user.getPassword())) {
            throw new HttpClientErrorException(
                    HttpStatus.FORBIDDEN,
                    messageSource.getMessage(
                            "auth.users.email_password_not_match",
                            null,
                            locale
                    )
            );
        }

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
    @Resource
    UserService userService;
    @Resource
    UserRepository userRepository;
    @Resource
    EncryptHelper encryptHelper;
    @Resource
    MessageSource messageSource;
}
