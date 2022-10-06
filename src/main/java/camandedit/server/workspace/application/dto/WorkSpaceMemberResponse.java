package camandedit.server.workspace.application.dto;

import camandedit.server.workspace.domain.MemberRole;
import camandedit.server.workspace.domain.WorkSpaceMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkSpaceMemberResponse {

  private Long userId;
  private String email;
  private String nickName;
  private MemberRole role;
  private String userImage;

  public static WorkSpaceMemberResponse from(WorkSpaceMember member) {
    return WorkSpaceMemberResponse.builder()
        .userId(member.getUser().getId())
        .email(member.getUser().getEmail())
        .nickName(member.getNickname())
        .role(member.getMemberRole())
        .userImage(member.getUser().getUserImage())
        .build();
  }
}
