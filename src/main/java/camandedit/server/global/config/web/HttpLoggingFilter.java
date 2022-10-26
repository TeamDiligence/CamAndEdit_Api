package camandedit.server.global.config.web;

import camandedit.server.global.util.RequestLogUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class HttpLoggingFilter extends OncePerRequestFilter {

  private final ObjectMapper objectMapper = new ObjectMapper();


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    ReusableRequestWrapper requestWrapper = new ReusableRequestWrapper(request);
    HttpAccessLogDto logging = logging(requestWrapper);
    log.info(objectMapper.writeValueAsString(logging));
    filterChain.doFilter(requestWrapper, response);
  }

  private HttpAccessLogDto logging(ReusableRequestWrapper request) throws IOException {
    return HttpAccessLogDto.builder()
        .method(request.getMethod())
        .url(request.getRequestURI())
        .ip(RequestLogUtil.getIp(request))
        .requestBody(RequestLogUtil.getBody(request))
        .build();
  }
}
