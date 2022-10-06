package camandedit.server.workspace.application.dto;

import camandedit.server.workspace.domain.WorkSpace;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkSpaceResponse {

  private Long workSpaceId;
  private String workSpaceName;
  private Long adminUserId;
  private String logoImage;
  private LocalDateTime createdAt;
  private List<MeetingRoomResponse> meetingRoomList;

  public static WorkSpaceResponse from(WorkSpace workSpace) {
    return WorkSpaceResponse.builder()
        .workSpaceId(workSpace.getId())
        .workSpaceName(workSpace.getName())
        .adminUserId(workSpace.getAdminId())
        .logoImage(workSpace.getLogoImage())
        .createdAt(workSpace.getCreatedAt())
        .build();
  }

  public static WorkSpaceResponse toDetail(WorkSpace workSpace) {
    return WorkSpaceResponse.builder()
        .workSpaceId(workSpace.getId())
        .workSpaceName(workSpace.getName())
        .adminUserId(workSpace.getAdminId())
        .logoImage(workSpace.getLogoImage())
        .createdAt(workSpace.getCreatedAt())
        .meetingRoomList(
            workSpace.getMeetingRoomList().stream().map(MeetingRoomResponse::from).toList())
        .build();
  }
}
