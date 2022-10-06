package camandedit.server.workspace.controller.dto;

import camandedit.server.workspace.application.command.InviteWorkSpaceCommand;

public record InviteWorkSpaceRequest(String email) {

  public InviteWorkSpaceCommand toCommand(Long adminId, Long workSpaceId){
    return InviteWorkSpaceCommand.builder()
        .adminId(adminId)
        .workSpaceId(workSpaceId)
        .email(email)
        .build();
  }
}
