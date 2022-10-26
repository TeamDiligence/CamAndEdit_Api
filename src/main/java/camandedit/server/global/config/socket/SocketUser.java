package camandedit.server.global.config.socket;

import java.security.Principal;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SocketUser implements Principal {

  private Long userId;
  private String sessionId;

  @Builder
  public SocketUser(Long userId, String sessionId) {
    this.userId = userId;
    this.sessionId = sessionId;
  }

  @Override
  public String getName() {
    return sessionId;
  }
}
