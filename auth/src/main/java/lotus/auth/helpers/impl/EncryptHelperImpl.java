package lotus.auth.helpers.impl;

import lotus.auth.helpers.EncryptHelper;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by flamen on 16-9-18.
 */
@Component("auth.encryptHelper")
public class EncryptHelperImpl implements EncryptHelper {
    @Override
    public String encode(String plain) {
        return textEncryptor.encrypt(plain);
    }

    @Override
    public String decode(String code) {
        return textEncryptor.decrypt(code);
    }

    @Override
    public String password(String plain) {
        return passwordEncryptor.encryptPassword(plain);
    }

    @Override
    public boolean check(String plain, String code) {
        return passwordEncryptor.checkPassword(plain, code);
    }

    @PostConstruct
    void init() {
        passwordEncryptor = new StrongPasswordEncryptor();

        StrongTextEncryptor ste = new StrongTextEncryptor();
        ste.setPassword(secrets);
        textEncryptor = ste;
    }


    @Value("${server.secrets}")
    String secrets;
    private PasswordEncryptor passwordEncryptor;
    private TextEncryptor textEncryptor;

}
