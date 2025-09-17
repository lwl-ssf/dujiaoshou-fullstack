\1
/**
 * JwtUtil
 * 模块：安全/鉴权
 * 说明：本类属于 dujiaoshou 项目，提供相应模块的核心能力。
 */
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
  public static String generate(String subject, Map<String, Object> claims, String secret, long ttlMillis) {
    Key key = Keys.hmacShaKeyFor(secret.getBytes());
    long now = System.currentTimeMillis();
    return Jwts.builder()
        .setSubject(subject)
        .addClaims(claims)
        .setIssuedAt(new Date(now))
        .setExpiration(new Date(now + ttlMillis))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  public static io.jsonwebtoken.Claims parse(String token, String secret) {
    return Jwts.parserBuilder().setSigningKey(secret.getBytes()).build()
        .parseClaimsJws(token).getBody();
  }
}
