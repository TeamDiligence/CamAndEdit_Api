package camandedit.server.global.security.filter;

import camandedit.server.user.domain.service.TokenProvider;
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

  private final TokenProvider tokenProvider;

  public JwtAuthenticationFilter(TokenProvider tokenProvider) {
    this.tokenProvider = tokenProvider;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String token = extractToken(request);
    if (StringUtils.hasText(token) && tokenProvider.validationToken(token)) {
      Authentication authentication = tokenProvider.getAuthentication(token);
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
