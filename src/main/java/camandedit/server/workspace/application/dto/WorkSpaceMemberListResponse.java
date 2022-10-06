package camandedit.server.workspace.application.dto;

import camandedit.server.workspace.domain.InviteMember;
import camandedit.server.workspace.domain.WorkSpaceMember;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkSpaceMemberListResponse {

  private List<WorkSpaceMemberResponse> memberList;
  private List<InviteMemberResponse> inviteList;

  public static WorkSpaceMemberListResponse from(List<WorkSpaceMember> memberList,
      List<InviteMember> inviteMemberList) {
    List<WorkSpaceMemberResponse> members = memberList.stream()
        .map(WorkSpaceMemberResponse::from).toList();
    List<InviteMemberResponse> invitesMembers = inviteMemberList.stream()
        .map(InviteMemberResponse::from)
        .toList();
    return new WorkSpaceMemberListResponse(members, invitesMembers);
  }
}
