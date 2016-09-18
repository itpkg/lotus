package lotus.auth.models;

import javax.persistence.*;

/**
 * Created by flamen on 16-9-18.
 */
@Entity
@Table(indexes = {
        @Index(name = "idx_locales_code_lang", columnList = "code,lang", unique = true),
        @Index(name="idx_locales_lang", columnList = "lang")
})
public class Locale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false, length = 6)
    private String lang;
    @Column(nullable = false)
    @Lob
    private String body;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
