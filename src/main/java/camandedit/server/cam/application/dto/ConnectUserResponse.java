package camandedit.server.cam.application.dto;

import camandedit.server.cam.domain.ConnectUser;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConnectUserResponse implements Serializable {

  private Long userId;
  private String nickName;
  private String userImage;

  public static ConnectUserResponse from(ConnectUser user) {
    return new ConnectUserResponse(user.getUserId(), user.getNickname(), user.getUserImage());
  }
}
