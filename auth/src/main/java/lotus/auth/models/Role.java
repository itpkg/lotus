package lotus.auth.models;

import javax.persistence.*;

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
public class Role extends Model {
    private String resourceType;
    private Long resourceId;
    @Column(updatable = false, nullable = false)
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


}
