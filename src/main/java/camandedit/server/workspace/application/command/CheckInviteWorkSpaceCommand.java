package camandedit.server.workspace.application.command;

import camandedit.server.global.common.SelfValidator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CheckInviteWorkSpaceCommand extends SelfValidator<CheckInviteWorkSpaceCommand> {

  @NotNull
  private Long userId;

  @NotNull
  private Long workSpaceId;

  @NotBlank
  @Email
  private String email;

  @Builder
  public CheckInviteWorkSpaceCommand(Long userId, Long workSpaceId, String email) {
    this.userId = userId;
    this.workSpaceId = workSpaceId;
    this.email = email;
    this.validationSelf();
  }
}
