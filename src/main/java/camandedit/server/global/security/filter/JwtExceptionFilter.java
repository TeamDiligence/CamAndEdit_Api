package camandedit.server.global.security.filter;

import camandedit.server.global.exception.BusinessException;
import camandedit.server.global.exception.ErrorType;
import camandedit.server.global.response.FailResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (BusinessException e) {
      ErrorType errorType = e.getErrorType();
      log.info(e.getMessage());
      responseHandle(response, errorType, e.getMessage());
    } catch (Exception e) {
      log.info(e.getMessage());
      responseHandle(response, ErrorType.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  private void responseHandle(HttpServletResponse response, ErrorType errorType, String message)
      throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");
    response.setStatus(errorType.getStatusCode());
    FailResponse failResponse = new FailResponse(false, message, errorType.getStatusCode());

    response.getWriter().write(objectMapper.writeValueAsString(failResponse));
  }
}
