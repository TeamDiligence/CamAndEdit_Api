package camandedit.server.global.security.jwt;

import camandedit.server.global.property.JwtProperty;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

  private final Key accessKey;
  private final int accessExpiredMin;

  public JwtProvider(JwtProperty jwtProperty) {
    byte[] accessKeyBytes = jwtProperty.getAccessKey().getBytes(StandardCharsets.UTF_8);
    this.accessKey = Keys.hmacShaKeyFor(accessKeyBytes);
    this.accessExpiredMin = jwtProperty.getAccessExpiredMin();
  }

  public String createAccessToken(Long userId) {
    Map<String, Object> payload = new HashMap<>();
    payload.put("userId", userId);

    return Jwts.builder()
        .addClaims(payload)
        .setExpiration(Date.from(Instant.now().plus(accessExpiredMin, ChronoUnit.MINUTES)))
        .signWith(accessKey)
        .compact();
  }
}
