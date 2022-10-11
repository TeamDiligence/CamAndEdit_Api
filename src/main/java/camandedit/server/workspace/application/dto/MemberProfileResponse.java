package camandedit.server.workspace.application.dto;

import camandedit.server.workspace.domain.MemberRole;
import camandedit.server.workspace.domain.WorkSpaceMember;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberProfileResponse {

  private Long userId;
  private String userImage;
  private String nickName;
  private String description;
  private LocalDateTime joinDate;
  private MemberRole role;

  public static MemberProfileResponse from(WorkSpaceMember workSpaceMember) {
    return MemberProfileResponse.builder()
        .userId(workSpaceMember.getUser().getId())
        .userImage(workSpaceMember.getUser().getUserImage())
        .nickName(workSpaceMember.getNickname())
        .joinDate(workSpaceMember.getCreatedAt())
        .role(workSpaceMember.getMemberRole())
        .description(workSpaceMember.getDescription())
        .build();
  }
}
