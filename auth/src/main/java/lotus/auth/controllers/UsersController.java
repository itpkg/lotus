package lotus.auth.controllers;

import lotus.auth.forms.ChangePasswordForm;
import lotus.auth.forms.EmailForm;
import lotus.auth.forms.SignInForm;
import lotus.auth.forms.SignUpForm;
import lotus.auth.helpers.EncryptHelper;
import lotus.auth.helpers.JwtHelper;
import lotus.auth.jobs.EmailJob;
import lotus.auth.models.User;
import lotus.auth.repositiries.UserRepository;
import lotus.auth.services.I18nService;
import lotus.auth.services.SettingService;
import lotus.auth.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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
        sendEmail(user, Action.CONFIRM, locale);
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
        sendEmail(user, Action.CONFIRM, locale);
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
        sendEmail(user, Action.UNLOCK, locale);
        return user;

    }

    @PostMapping("/forgot-password")
    User postForgotPassword(Locale locale, @Valid EmailForm form) {
        User user = userService.getUserByEmail(form.getEmail(), locale);
        sendEmail(user, Action.CHANGE_PASSWORD, locale);
        return user;
    }

    @PostMapping("/change-password/{token}")
    User postChangePassword(@PathVariable String token, Locale locale, @Valid ChangePasswordForm form) {
        Map<String, Object> map = jwtHelper.parse(token);
        if (Action.valueOf(map.get("act").toString()) != Action.CHANGE_PASSWORD) {
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

    @PatchMapping("/confirm/{token}")
    User patchConfirm(@PathVariable String token, Locale locale) {
        Map<String, Object> map = jwtHelper.parse(token);
        if (Action.valueOf(map.get("act").toString()) != Action.CONFIRM) {
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

    @PatchMapping("/unlock/{token}")
    User patchUnlock(@PathVariable String token, Locale locale) {
        Map<String, Object> map = jwtHelper.parse(token);
        if (Action.valueOf(map.get("act").toString()) != Action.UNLOCK) {
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

    private enum Action {
        CONFIRM,
        UNLOCK,
        CHANGE_PASSWORD
    }

    private void sendEmail(User user, Action action, Locale locale) {
        logger.debug("send {} mail to {}", action.name(), user.getEmail());
        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", user.getUid());
        claims.put("act", action.name());
        String token = jwtHelper.sum(claims, 1);

        EmailJob ej = new EmailJob();
        ej.setTo(user.getEmail());
        String link;
        switch (action) {
            case CONFIRM:
                link = String.format("%s/users/confirm/%s", frontHome, token);
                break;
            case UNLOCK:
                link = String.format("%s/users/confirm/%s", frontHome, token);
                break;
            case CHANGE_PASSWORD:
                link = String.format("%s/users/change-password/%s", frontHome, token);
                break;
            default:
                throw new HttpClientErrorException(
                        HttpStatus.FORBIDDEN,
                        messageSource.getMessage(
                                "errors.back_token",
                                null,
                                locale
                        )
                );
        }
        ej.setTitle(messageSource.getMessage(
                String.format("auth.emails.%s.title", action.name().toLowerCase()),
                new Object[]{
                        i18nService.t(locale, "site.title")
                },
                locale
        ));
        ej.setBody(messageSource.getMessage(
                String.format("auth.emails.%s.body", action.name().toLowerCase()),
                new Object[]{
                        link
                },
                locale
        ));
        redisTemplate.convertAndSend("email", ej);
    }


    @Resource
    SettingService settingService;
    @Resource
    I18nService i18nService;
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
    @Resource
    RedisTemplate redisTemplate;
    @Value("${server.front.home}")
    String frontHome;
    @Value("${server.backend.home}")
    String backendHome;
    private final static Logger logger = LoggerFactory.getLogger(UsersController.class);
}
