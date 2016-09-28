package lotus.auth.services;

import lotus.auth.helpers.EncryptHelper;
import lotus.auth.helpers.GravatarHelper;
import lotus.auth.models.User;
import lotus.auth.repositiries.UserRepository;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by flamen on 16-9-19.
 */
@Service("auth.userService")
public class UserService {

    public User getUserByUid(String uid, Locale locale) {
        User user = userRepository.findByUid(uid);
        if (user == null) {
            throw new HttpClientErrorException(
                    HttpStatus.FORBIDDEN,
                    messageSource.getMessage(
                            "auth.users.user_not_exists",
                            null,
                            locale
                    )
            );
        }
        return user;
    }

    public User getUserByEmail(String email, Locale locale) {
        User user = userRepository.findByProviderIdAndProviderType(email, User.Type.EMAIL);
        if (user == null) {
            throw new HttpClientErrorException(
                    HttpStatus.FORBIDDEN,
                    messageSource.getMessage(
                            "auth.users.email_not_exists",
                            new Object[]{email},
                            locale
                    )
            );
        }
        return user;
    }

    public User add(String email, String name, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        User u = new User();
        u.setEmail(email);
        u.setName(name);
        u.setPassword(encryptHelper.password(password));

        u.setProviderType(User.Type.EMAIL);
        u.setProviderId(email);
        u.setUid(UUID.randomUUID().toString());
        u.setLogo(gravatarHelper.logo(email));
        userRepository.save(u);
        return u;
    }

    @Resource
    UserRepository userRepository;
    @Resource
    GravatarHelper gravatarHelper;
    @Resource
    EncryptHelper encryptHelper;
    @Resource
    MessageSource messageSource;
}
