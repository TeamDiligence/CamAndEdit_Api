package camandedit.server.workspace.controller.dto;

import camandedit.server.workspace.application.command.CheckInviteWorkSpaceCommand;

public record CheckInviteRequest(String email) {

  public CheckInviteWorkSpaceCommand toCommand(Long userId, Long workspaceId){
    return CheckInviteWorkSpaceCommand.builder()
        .userId(userId)
        .workSpaceId(workspaceId)
        .email(email)
        .build();
  }
}
