package camandedit.server.cam.controller.dto;

import camandedit.server.cam.application.dto.RoomCurrentUserResponse;
import camandedit.server.cam.infra.WorkSpacePublisherType;
import camandedit.server.cam.infra.WorkSpacePublisherType.Values;
import camandedit.server.cam.infra.dto.WorkSpacePublisherDto;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeName(Values.ROOM)
public class RoomCurrentUserDto extends WorkSpacePublisherDto implements Serializable {
  public RoomCurrentUserDto(WorkSpacePublisherType type, Long workspaceId,
      List<RoomCurrentUserResponse> roomCurrentUserList) {
    this.type = type;
    this.workspaceId = workspaceId;
    this.value = roomCurrentUserList;
  }
}
