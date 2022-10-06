package camandedit.server.workspace.domain.service;

import camandedit.server.global.exception.NotFoundResourceException;
import camandedit.server.workspace.domain.InviteMember;
import camandedit.server.workspace.domain.repository.InviteMemberRepository;
import camandedit.server.workspace.exception.AlreadyInviteEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InviteMemberChecker {

  private final InviteMemberRepository inviteMemberRepository;

  public void checkAlreadyInvite(String email, Long workSpaceId) {
    InviteMember inviteMember = inviteMemberRepository.findNotInviteMember(workSpaceId, email);
    if (inviteMember != null) {
      throw new AlreadyInviteEmailException("이미 초대 메일을 보냈습니다.");
    }
  }

  public InviteMember checkExistInvite(String email, Long workSpaceId) {
    InviteMember inviteMember = inviteMemberRepository.findNotInviteMember(workSpaceId, email);
    if (inviteMember == null) {
      throw new NotFoundResourceException("초대 정보를 확인할 수 없습니다.");
    }
    return inviteMember;
  }
}
