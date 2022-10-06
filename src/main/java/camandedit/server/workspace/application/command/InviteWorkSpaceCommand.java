package camandedit.server.workspace.application.command;

import camandedit.server.global.common.SelfValidator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InviteWorkSpaceCommand extends SelfValidator<InviteWorkSpaceCommand> {

  @NotNull
  private Long adminId;

  @NotNull
  private Long workSpaceId;

  @NotBlank
  @Email
  private String email;

  @Builder
  public InviteWorkSpaceCommand(Long adminId, Long workSpaceId, String email) {
    this.adminId = adminId;
    this.workSpaceId = workSpaceId;
    this.email = email;
    this.validationSelf();
  }
}
