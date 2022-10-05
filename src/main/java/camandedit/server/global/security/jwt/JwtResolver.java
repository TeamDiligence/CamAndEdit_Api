package camandedit.server.global.security.jwt;

import camandedit.server.global.property.JwtProperty;
import camandedit.server.global.security.exception.ExpiredJwtFailException;
import camandedit.server.global.security.exception.InvalidJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtResolver {

  private Key accessKey;

  public JwtResolver(JwtProperty jwtProperty) {
    byte[] accessKeyBytes = jwtProperty.getAccessKey().getBytes(StandardCharsets.UTF_8);
    this.accessKey = Keys.hmacShaKeyFor(accessKeyBytes);
  }


  public Authentication getAuthentication(String token) {
    Claims claims = Jwts.parserBuilder().setSigningKey(accessKey).build().parseClaimsJws(token)
        .getBody();
    String userId = claims.get("userId").toString();
    return new UsernamePasswordAuthenticationToken(userId, "", null);
  }

  public boolean validationAccessToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(accessKey).build().parse(token);
      return true;
    } catch (SecurityException | MalformedJwtException | SignatureException e) {
      log.error("잘못된 JWT 서명");
      throw new InvalidJwtException("잘못된 JWT 서명");
    } catch (UnsupportedJwtException e) {
      log.error("지원하지 않는 JWT 토큰");
      throw new InvalidJwtException("지원하지 않는 JWT 토큰");
    } catch (IllegalArgumentException e) {
      log.error("잘못된 토큰 값 ");
      throw new InvalidJwtException("잘못된 토큰 값");
    } catch (ExpiredJwtException e) {
      log.error("JWT 값 만료");
      throw new ExpiredJwtFailException("만료된 JWT 토큰입니다");
    }
  }

}
