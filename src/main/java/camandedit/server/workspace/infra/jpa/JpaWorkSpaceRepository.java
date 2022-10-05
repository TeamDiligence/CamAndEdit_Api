package camandedit.server.workspace.infra.jpa;

import camandedit.server.workspace.domain.WorkSpace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWorkSpaceRepository extends JpaRepository<WorkSpace, Long> {

}
