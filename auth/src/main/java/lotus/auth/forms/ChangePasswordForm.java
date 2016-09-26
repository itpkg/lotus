package lotus.auth.forms;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import java.io.Serializable;

/**
 * Created by flamen on 16-9-25.
 */
public class ChangePasswordForm implements Serializable {
    @NotEmpty
    @Length(min = 6, max = 32)
    private String password;
    private String passwordConfirm;


    @AssertTrue(message = "passwordConfirm field should be equal than password field")
    boolean isValid() {
        return this.password.equals(this.passwordConfirm);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
