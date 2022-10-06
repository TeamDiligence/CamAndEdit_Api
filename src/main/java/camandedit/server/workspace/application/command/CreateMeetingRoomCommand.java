package camandedit.server.workspace.application.command;

import camandedit.server.global.common.SelfValidator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateMeetingRoomCommand extends SelfValidator<CreateMeetingRoomCommand> {

  @NotNull
  private Long workSpaceId;

  @NotNull
  private Long userId;

  @NotBlank
  private String name;

  @Builder
  public CreateMeetingRoomCommand(Long workSpaceId, Long userId, String name) {
    this.workSpaceId = workSpaceId;
    this.userId = userId;
    this.name = name;
    this.validationSelf();
  }
}
