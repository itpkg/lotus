package lotus.auth.helpers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

/**
 * Created by flamen on 16-9-26.
 */
@Component("auth.jwtHelper")
public class JwtHelper {
    public String sum(Map<String, Object> claims, int hours) {
        LocalDateTime now = LocalDateTime.now();
        return Jwts.builder()
                .setSubject(subject)
                .setClaims(claims)
                .setNotBefore(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(now.plusHours(hours).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, key.getBytes())
                .compact();
    }

    public Map<String, Object> parse(String token) {
        return Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(token).getBody();
    }

    @Value("${server.jwt.key}")
    String key;

    @Value("${server.jwt.subject}")
    String subject;
}
