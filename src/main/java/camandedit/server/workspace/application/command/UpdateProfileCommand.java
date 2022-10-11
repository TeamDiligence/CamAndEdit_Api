package camandedit.server.workspace.application.command;

import camandedit.server.global.common.SelfValidator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateProfileCommand extends SelfValidator<UpdateProfileCommand> {

  @NotBlank
  private String nickname;

  @NotBlank
  private String description;

  @NotNull
  private Long userId;

  @NotNull
  private Long workSpaceId;

  @Builder
  public UpdateProfileCommand(String nickname, String description, Long userId, Long workSpaceId) {
    this.nickname = nickname;
    this.description = description;
    this.userId = userId;
    this.workSpaceId = workSpaceId;
    this.validationSelf();
  }
}
