package lotus.auth.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import java.io.Serializable;

/**
 * Created by flamen on 16-9-25.
 */
public class SignUpForm implements Serializable {
    @NotEmpty
    @Length(max = 255)
    private String name;
    @NotEmpty
    @Email
    @Length(max = 255)
    private String email;
    @NotEmpty
    @Length(min = 6, max = 32)
    private String password;
    private String passwordConfirm;


    @AssertTrue(message = "passwordConfirm field should be equal than password field")
    boolean isValid() {
        return this.password.equals(this.passwordConfirm);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
