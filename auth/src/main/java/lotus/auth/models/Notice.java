package lotus.auth.models;

import javax.persistence.*;

/**
 * Created by flamen on 16-9-18.
 */

@Entity
@Table(name = "notices")
public class Notice extends Model {
    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
