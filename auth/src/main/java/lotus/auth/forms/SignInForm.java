package lotus.auth.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by flamen on 16-9-25.
 */
public class SignInForm implements Serializable {
    @NotEmpty
    @Email
    @Length(max = 255)
    private String email;
    @NotEmpty
    @Length(min = 6, max = 32)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
