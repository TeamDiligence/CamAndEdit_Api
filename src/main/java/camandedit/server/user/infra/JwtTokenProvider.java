package camandedit.server.user.infra;

import camandedit.server.global.property.JwtProperty;
import camandedit.server.user.exception.ExpiredJwtFailException;
import camandedit.server.user.exception.InvalidJwtException;
import camandedit.server.user.domain.User;
import camandedit.server.user.domain.service.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtTokenProvider implements TokenProvider {

  private final Key accessKey;
  private final int accessExpiredMin;

  public JwtTokenProvider(JwtProperty jwtProperty) {
    byte[] accessKeyBytes = jwtProperty.getAccessKey().getBytes(StandardCharsets.UTF_8);
    this.accessKey = Keys.hmacShaKeyFor(accessKeyBytes);
    this.accessExpiredMin = jwtProperty.getAccessExpiredMin();
  }

  @Override
  public String createToken(User user) {
    Map<String, Object> payload = new HashMap<>();
    payload.put("userId", user.getId());

    return Jwts.builder()
        .addClaims(payload)
        .setExpiration(Date.from(Instant.now().plus(accessExpiredMin, ChronoUnit.MINUTES)))
        .signWith(accessKey)
        .compact();
  }

  @Override
  public boolean validationToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(accessKey).build().parse(token);
      return true;
    } catch (SecurityException | MalformedJwtException | SignatureException e) {
      log.error("????????? JWT ??????");
      throw new InvalidJwtException("????????? JWT ??????");
    } catch (UnsupportedJwtException e) {
      log.error("???????????? ?????? JWT ??????");
      throw new InvalidJwtException("???????????? ?????? JWT ??????");
    } catch (IllegalArgumentException e) {
      log.error("????????? ?????? ??? ");
      throw new InvalidJwtException("????????? ?????? ???");
    } catch (ExpiredJwtException e) {
      log.error("JWT ??? ??????");
      throw new ExpiredJwtFailException("????????? JWT ???????????????");
    }
  }

  @Override
  public Authentication getAuthentication(String token) {
    Claims claims = Jwts.parserBuilder().setSigningKey(accessKey).build().parseClaimsJws(token)
        .getBody();
    String userId = claims.get("userId").toString();
    return new UsernamePasswordAuthenticationToken(userId, "", null);
  }
}
