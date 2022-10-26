package camandedit.server.cam.controller.dto;

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
@JsonTypeName(Values.CHAT)
public class WorkSpaceChatDto extends WorkSpacePublisherDto implements Serializable {


  public WorkSpaceChatDto(WorkSpacePublisherType type, Long workSpaceId, ChatDto chatDtoList) {
    this.type = type;
    this.workspaceId = workSpaceId;
    this.value = chatDtoList;
  }
}
