package camandedit.server.global.config.web;

import lombok.Builder;
import lombok.Getter;

@Getter
public class HttpAccessLogDto {

  private String method;

  private String url;

  private String ip;

  private String requestBody;

  @Builder
  public HttpAccessLogDto(String method, String url, String ip, String requestBody) {
    this.method = method;
    this.url = url;
    this.ip = ip;
    this.requestBody = requestBody;
  }
}
