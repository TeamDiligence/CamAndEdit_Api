package camandedit.server.workspace.infra.jpa;

import camandedit.server.workspace.domain.WorkSpaceMember;
import camandedit.server.workspace.domain.WorkSpaceMemberKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWorkSpaceMemberRepository extends
    JpaRepository<WorkSpaceMember, WorkSpaceMemberKey> {

}
