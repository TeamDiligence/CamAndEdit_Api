package camandedit.server.global.security.handler;

import camandedit.server.global.exception.ErrorType;
import camandedit.server.global.response.FailResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    log.info(authException.getMessage());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");
    response.setStatus(ErrorType.AUTHENTICATION_FAIL.getStatusCode());
    FailResponse failResponse = new FailResponse(false, "인증 실패", ErrorType.AUTHENTICATION_FAIL.getStatusCode());

    response.getWriter().write(objectMapper.writeValueAsString(failResponse));
  }
}
