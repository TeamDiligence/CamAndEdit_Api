package camandedit.server.cam.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatDto {

  private Long senderId;
  private String nickname;
  private String userImage;
  private String message;

  @Builder
  public ChatDto(Long senderId, String nickname, String userImage, String message) {
    this.senderId = senderId;
    this.nickname = nickname;
    this.userImage = userImage;
    this.message = message;
  }
}
