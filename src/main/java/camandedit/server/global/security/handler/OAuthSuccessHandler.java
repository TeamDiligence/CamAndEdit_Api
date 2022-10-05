package camandedit.server.global.security.handler;

import camandedit.server.global.security.dto.OAuthUserPrincipal;
import camandedit.server.global.security.jwt.JwtProvider;
import com.sun.security.auth.UserPrincipal;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

  private final JwtProvider jwtProvider;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    OAuthUserPrincipal userPrincipal = (OAuthUserPrincipal) authentication.getPrincipal();
    String accessToken = jwtProvider.createAccessToken(userPrincipal.getUserId());

    response.setStatus(HttpStatus.OK.value());
    response.sendRedirect("http://localhost:3000/auth?accessToken="+accessToken);
  }

}
