package camandedit.server.workspace.controller.dto;

import camandedit.server.workspace.application.command.CreateMeetingRoomCommand;

public record CreateMeetingRoomRequest(Long workSpaceId, String roomName) {

  public CreateMeetingRoomCommand toCommand(Long userId){
    return CreateMeetingRoomCommand.builder()
        .workSpaceId(workSpaceId)
        .userId(userId)
        .name(roomName)
        .build();
  }
}
