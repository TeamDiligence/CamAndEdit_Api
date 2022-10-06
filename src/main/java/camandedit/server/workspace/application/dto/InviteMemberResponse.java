package camandedit.server.workspace.application.dto;

import camandedit.server.workspace.domain.InviteMember;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InviteMemberResponse {

  private String email;
  private LocalDateTime inviteRequestTime;

  public static InviteMemberResponse from(InviteMember inviteMember){
    return new InviteMemberResponse(inviteMember.getEmail(), inviteMember.getInviteRequestTime());
  }
}
