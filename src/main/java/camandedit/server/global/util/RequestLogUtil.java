package camandedit.server.global.util;

import camandedit.server.global.config.web.ReusableRequestWrapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import org.springframework.util.StreamUtils;

public class RequestLogUtil {

  public static String getIp(HttpServletRequest request) {
    String ip = request.getHeader("X-Forwarded-For");
    if (ip == null) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }

  public static String getBody(ReusableRequestWrapper request) throws  IOException {
    String method = request.getMethod().toUpperCase();
    String contentType = request.getContentType();
    if (contentType == null) {
      return null;
    }

    if (method.startsWith("P") && contentType.contains("application/json")) {
      ServletInputStream inputStream = request.getInputStream();
      String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
      return messageBody;
    } else {
      return null;
    }
  }
}
