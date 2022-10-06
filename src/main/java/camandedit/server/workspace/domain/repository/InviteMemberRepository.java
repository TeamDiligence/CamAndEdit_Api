package camandedit.server.workspace.domain.repository;

import camandedit.server.workspace.domain.InviteMember;
import java.util.List;

public interface InviteMemberRepository {

  void save(InviteMember inviteMember);
  List<InviteMember> findNotInvitedMemberList(Long workSpaceId);
  InviteMember findNotInviteMember(Long workSpaceId, String email);
}
