package lotus.auth.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by flamen on 16-9-18.
 */
@Entity
@Table(name = "policies", indexes = {
        @Index(columnList = "role_id,user_id", unique = true),
}
)
public class Policy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
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
    @Column(nullable = false)
    private Date updatedAt;
    @Column(nullable = false, updatable = false)
    private Date createdAt;


    @PrePersist
    protected void onCreate() {
        updatedAt = createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
