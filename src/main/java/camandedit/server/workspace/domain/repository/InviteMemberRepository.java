package camandedit.server.workspace.domain.repository;

import camandedit.server.workspace.domain.InviteMember;
import java.util.List;

public interface InviteMemberRepository {

  void save(InviteMember inviteMember);
  List<InviteMember> findNotApproveMemberList(Long workSpaceId);
  InviteMember findApproveInviteMember(Long workSpaceId, String email);
}
