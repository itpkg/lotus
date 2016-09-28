package lotus.auth.models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by flamen on 16-9-18.
 */
@Entity
@Table(name = "policies", indexes = {
        @Index(columnList = "role_id,user_id", unique = true),
}
)
public class Policy extends Model {
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Role role;
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private User user;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startUp;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date shutDown;

    public Date getStartUp() {
        return startUp;
    }

    public void setStartUp(Date startUp) {
        this.startUp = startUp;
    }

    public Date getShutDown() {
        return shutDown;
    }

    public void setShutDown(Date shutDown) {
        this.shutDown = shutDown;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
