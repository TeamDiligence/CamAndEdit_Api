package camandedit.server.workspace.application.dto;

import camandedit.server.workspace.domain.MeetingRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingRoomResponse {

  private Long meetingRoomId;

  private String meetingRoomName;

  public static MeetingRoomResponse from(MeetingRoom meetingRoom) {
    return new MeetingRoomResponse(meetingRoom.getId(), meetingRoom.getName());
  }
}
