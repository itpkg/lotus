package lotus.auth.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by flamen on 16-9-18.
 */
@Entity
@Table(name = "roles", indexes = {
        @Index(columnList = "resourceId,resourceType,name", unique = true),
        @Index(columnList = "resourceType"),
        @Index(columnList = "name"),
}
)
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String resourceType;
    private Long resourceId;
    @Column(updatable = false, nullable = false)
    private String name;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
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
