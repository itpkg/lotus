package lotus.auth.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by flamen on 16-9-18.
 */

@Entity
public class Setting implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, unique = true)
    private String key;
    @Column(nullable = false)
    @Lob
    private byte[] val;
    @Column(nullable = false)
    private boolean encode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public byte[] getVal() {
        return val;
    }

    public void setVal(byte[] val) {
        this.val = val;
    }

    public boolean isEncode() {
        return encode;
    }

    public void setEncode(boolean encode) {
        this.encode = encode;
    }
}
