package camandedit.server.cam.infra.dto;

import camandedit.server.cam.controller.dto.RoomCurrentUserDto;
import camandedit.server.cam.controller.dto.WorkSpaceChatDto;
import camandedit.server.cam.infra.WorkSpacePublisherType;
import camandedit.server.cam.infra.WorkSpacePublisherType.Values;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import java.io.Serializable;
import lombok.Getter;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = As.PROPERTY,
    property = "pubType"
)
@JsonSubTypes({
    @Type(value = RoomCurrentUserDto.class, name = Values.ROOM),
    @Type(value = WorkSpaceChatDto.class, name = Values.CHAT),
})
@Getter
public abstract class WorkSpacePublisherDto implements Serializable {

  public WorkSpacePublisherType type;
  public Long workspaceId;
  public Object value;
}
