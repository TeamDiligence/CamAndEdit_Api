package camandedit.server.workspace.infra;

import camandedit.server.workspace.domain.InviteMember;
import camandedit.server.workspace.domain.repository.InviteMemberRepository;
import camandedit.server.workspace.infra.jpa.JpaInviteMemberRepository;
import camandedit.server.workspace.infra.query.InviteQueryRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InviteMemberRepositoryImpl implements InviteMemberRepository {

  private final JpaInviteMemberRepository jpaInviteMemberRepository;
  private final InviteQueryRepository inviteQueryRepository;
  @Override
  public void save(InviteMember inviteMember) {
    jpaInviteMemberRepository.save(inviteMember);
  }

  @Override
  public List<InviteMember> findNotInvitedMemberList(Long workSpaceId) {
    return inviteQueryRepository.findAllInviteNotApproveMember(workSpaceId);
  }

  @Override
  public InviteMember findNotInviteMember(Long workSpaceId, String email) {
    return inviteQueryRepository.findByEamilAndWorkSpaceId(email,workSpaceId);
  }
}
