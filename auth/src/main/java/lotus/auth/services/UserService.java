package lotus.auth.services;

import lotus.auth.helpers.EncryptHelper;
import lotus.auth.helpers.GravatarHelper;
import lotus.auth.models.User;
import lotus.auth.repositiries.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by flamen on 16-9-19.
 */
@Service("auth.userService")
public class UserService {

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
}
