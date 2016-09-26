package lotus.auth.controllers;

import lotus.auth.forms.ChangePasswordForm;
import lotus.auth.forms.EmailForm;
import lotus.auth.forms.SignInForm;
import lotus.auth.forms.SignUpForm;
import lotus.auth.helpers.EncryptHelper;
import lotus.auth.helpers.JwtHelper;
import lotus.auth.models.User;
import lotus.auth.repositiries.UserRepository;
import lotus.auth.services.SettingService;
import lotus.auth.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by flamen on 16-9-18.
 */
@RestController("auth.usersController")
@RequestMapping(path = "/users")
public class UsersController {
    @PostMapping("/sign-in")
    String postSignIn(Locale locale, @Valid SignInForm form) {

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
        if (!user.isAvailable()) {
            throw new HttpClientErrorException(
                    HttpStatus.FORBIDDEN,
                    messageSource.getMessage(
                            "auth.users.not_available",
                            null,
                            locale
                    )
            );
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", user.getUid());
        claims.put("name", user.getName());

        return jwtHelper.sum(claims, 24 * 7);
    }

    @PostMapping("/sign-up")
    User postSignUp(Locale locale, @Valid SignUpForm form) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        User user = userRepository.findByProviderIdAndProviderType(form.getEmail(), User.Type.EMAIL);
        if (user != null) {
            throw new HttpClientErrorException(
                    HttpStatus.FORBIDDEN,
                    messageSource.getMessage(
                            "auth.users.email_already_exists",
                            new Object[]{form.getEmail()},
                            locale
                    )
            );
        }
        user = userService.add(form.getEmail(), form.getName(), form.getPassword());
        sendEmail(user, Action.CONFIRM);
        return user;

    }

    @PostMapping("/confirm")
    User postConfirm(Locale locale, @Valid EmailForm form) {
        User user = userService.getUserByEmail(form.getEmail(), locale);
        if (user.isConfirmed()) {
            throw new HttpClientErrorException(
                    HttpStatus.FORBIDDEN,
                    messageSource.getMessage(
                            "auth.users.was_confirmed",
                            null,
                            locale
                    )
            );
        }
        sendEmail(user, Action.CONFIRM);
        return user;
    }

    @PostMapping("/unlock")
    User postUnlock(Locale locale, @Valid EmailForm form) {
        User user = userService.getUserByEmail(form.getEmail(), locale);
        if (!user.isLocked()) {
            throw new HttpClientErrorException(
                    HttpStatus.FORBIDDEN,
                    messageSource.getMessage(
                            "auth.users.not_lock",
                            null,
                            locale
                    )
            );
        }
        sendEmail(user, Action.UNLOCK);
        return user;

    }

    @PostMapping("/forgot-password")
    User postForgotPassword(Locale locale, @Valid EmailForm form) {
        User user = userService.getUserByEmail(form.getEmail(), locale);
        sendEmail(user, Action.CHANGE_PASSWORD);
        return user;
    }

    @PostMapping("/change-password/{token}")
    User postChangePassword(@PathVariable String token, Locale locale, @Valid ChangePasswordForm form) {
        Map<String, Object> map = jwtHelper.parse(token);
        if(Action.valueOf(map.get("act").toString()) != Action.CHANGE_PASSWORD){
            throw new HttpClientErrorException(
                    HttpStatus.FORBIDDEN,
                    messageSource.getMessage(
                            "errors.back_token",
                            null,
                            locale
                    )
            );
        }
        User user = userService.getUserByUid(map.get("uid").toString(), locale);
        user.setPassword(encryptHelper.password(form.getPassword()));
        userRepository.save(user);
        return user;

    }

    @GetMapping("/confirm/{token}")
    User getConfirm(@PathVariable String token, Locale locale) {
        Map<String, Object> map = jwtHelper.parse(token);
        if(Action.valueOf(map.get("act").toString()) != Action.CONFIRM){
            throw new HttpClientErrorException(
                    HttpStatus.FORBIDDEN,
                    messageSource.getMessage(
                            "errors.back_token",
                            null,
                            locale
                    )
            );
        }
        User user = userService.getUserByUid(map.get("uid").toString(), locale);
        if (user.isConfirmed()) {
            throw new HttpClientErrorException(
                    HttpStatus.FORBIDDEN,
                    messageSource.getMessage(
                            "auth.users.was_confirmed",
                            null,
                            locale
                    )
            );
        }
        user.setConfirmedAt(new Date());
        userRepository.save(user);
        return user;
    }

    @GetMapping("/unlock/{token}")
    User getUnlock(@PathVariable String token, Locale locale) {
        Map<String, Object> map = jwtHelper.parse(token);
        if(Action.valueOf(map.get("act").toString()) != Action.UNLOCK){
            throw new HttpClientErrorException(
                    HttpStatus.FORBIDDEN,
                    messageSource.getMessage(
                            "errors.back_token",
                            null,
                            locale
                    )
            );
        }
        User user = userService.getUserByUid(map.get("uid").toString(), locale);
        if (!user.isLocked()) {
            throw new HttpClientErrorException(
                    HttpStatus.FORBIDDEN,
                    messageSource.getMessage(
                            "auth.users.was_confirmed",
                            null,
                            locale
                    )
            );
        }
        user.setLockedAt(null);
        userRepository.save(user);
        return user;
    }

    public enum Action {
        CONFIRM,
        UNLOCK,
        CHANGE_PASSWORD
    }

    private void sendEmail(User user, Action action) {
        logger.debug("send {} mail to {}", action.name(), user.getEmail());

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
    @Resource
    JwtHelper jwtHelper;
    private final static Logger logger = LoggerFactory.getLogger(UsersController.class);
}
