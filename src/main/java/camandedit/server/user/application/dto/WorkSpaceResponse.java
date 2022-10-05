package camandedit.server.user.application.dto;

import camandedit.server.workspace.domain.WorkSpace;
import java.time.LocalDateTime;
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

  public static WorkSpaceResponse from(WorkSpace workSpace){
    return WorkSpaceResponse.builder()
        .workSpaceId(workSpace.getId())
        .workSpaceName(workSpace.getName())
        .adminUserId(workSpace.getAdminId())
        .logoImage(workSpace.getLogoImage())
        .createdAt(workSpace.getCreatedAt())
        .build();
  }
}
