package lotus.auth.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by flamen on 16-9-19.
 */

public class InstallForm implements Serializable {
    @NotEmpty
    @Length(max = 255)
    private String username;
    @NotEmpty
    @Email
    @Length(max = 255)
    private String email;
    @NotEmpty
    @Length(min = 6, max = 32)
    private String password;
    private String passwordConfirm;
    @NotEmpty
    @Size(max = 255)
    private String domain;
    private boolean https;

    @AssertTrue(message = "passwordConfirm field should be equal than password field")
    boolean isValid() {
        return this.password.equals(this.passwordConfirm);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public boolean isHttps() {
        return https;
    }

    public void setHttps(boolean https) {
        this.https = https;
    }
}
