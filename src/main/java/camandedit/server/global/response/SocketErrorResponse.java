package camandedit.server.global.response;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SocketErrorResponse implements Serializable {
  private String message;
  private String cause;
  private String sessionId;
}
