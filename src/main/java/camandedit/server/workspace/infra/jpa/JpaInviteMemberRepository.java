package camandedit.server.workspace.infra.jpa;

import camandedit.server.workspace.domain.InviteMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaInviteMemberRepository extends JpaRepository<InviteMember, Long> {

}
