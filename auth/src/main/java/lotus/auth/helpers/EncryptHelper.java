package lotus.auth.helpers;

/**
 * Created by flamen on 16-9-18.
 */
public interface EncryptHelper {
    String encode(String plain);

    String decode(String code);

    String password(String plain);

    boolean check(String plain, String code);
}
