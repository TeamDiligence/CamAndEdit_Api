package camandedit.server.workspace.controller.dto;

import camandedit.server.workspace.application.command.UpdateProfileCommand;

public record UpdateProfileRequest(String nickname, String description) {

  public UpdateProfileCommand toCommand(Long userId, Long workSpaceId){
    return UpdateProfileCommand.builder()
        .nickname(nickname)
        .description(description)
        .userId(userId)
        .workSpaceId(workSpaceId)
        .build();
  }

}
