package camandedit.server.user.application.dto;

import camandedit.server.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponse {

  private Long userId;
  private String email;
  private String name;
  private String userImage;

  @Builder
  private UserResponse(Long userId, String email, String name, String userImage) {
    this.userId = userId;
    this.email = email;
    this.name = name;
    this.userImage = userImage;
  }

  public static UserResponse from(User user){
    return UserResponse.builder()
        .userId(user.getId())
        .email(user.getEmail())
        .name(user.getName())
        .userImage(user.getUserImage())
        .build();
  }
}
