package lotus.auth.helpers;

import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by flamen on 16-9-19.
 */
@Component("auth.gravatarHelper")
public class GravatarHelper {
    public String logo(String email) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return Hex.encodeHexString(MessageDigest.getInstance("MD5").digest(email.getBytes("CP1252")));
    }
}
