package camandedit.server.cam.application.dto;

import camandedit.server.cam.domain.CamMeetingRoom;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoomCurrentUserResponse implements Serializable {

  private Long meetingRoomId;
  private List<ConnectUserResponse> userList = new ArrayList<>();

  public RoomCurrentUserResponse(Long meetingRoomId, List<ConnectUserResponse> userList) {
    this.meetingRoomId = meetingRoomId;
    this.userList.addAll(userList);
  }

  public static RoomCurrentUserResponse from(CamMeetingRoom room) {
    return new RoomCurrentUserResponse(room.getRoomId(),
        room.getConnectUsers().stream().map(ConnectUserResponse::from).toList());
  }
}
