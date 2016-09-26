package lotus.auth.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Created by flamen on 16-9-18.
 */
@Entity
@Table(name = "locales",
        indexes = {
                @Index(columnList = "code,lang", unique = true),
                @Index(columnList = "lang"),
                @Index(columnList = "code"),
        })
public class Locale extends Model {
    @Column(nullable = false)
    private String code;
    @Column(nullable = false, length = 6)
    private String lang;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
