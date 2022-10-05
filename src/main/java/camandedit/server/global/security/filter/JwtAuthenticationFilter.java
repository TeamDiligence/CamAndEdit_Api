package camandedit.server.global.security.filter;

import camandedit.server.global.security.jwt.JwtResolver;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtResolver jwtResolver;

  public JwtAuthenticationFilter(JwtResolver jwtResolver) {
    this.jwtResolver = jwtResolver;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String token = extractToken(request);
    if (StringUtils.hasText(token) && jwtResolver.validationAccessToken(token)) {
      Authentication authentication = jwtResolver.getAuthentication(token);
      SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
      emptyContext.setAuthentication(authentication);
      SecurityContextHolder.setContext(emptyContext);
    }
    filterChain.doFilter(request, response);
  }

  private String extractToken(HttpServletRequest request) {
    String bearerHeader = request.getHeader("Authorization");
    String HEADER_PREFIX = "Bearer ";
    if (StringUtils.hasText(bearerHeader) && bearerHeader.startsWith(HEADER_PREFIX)) {
      return bearerHeader.substring(HEADER_PREFIX.length());
    }
    return null;
  }
}
