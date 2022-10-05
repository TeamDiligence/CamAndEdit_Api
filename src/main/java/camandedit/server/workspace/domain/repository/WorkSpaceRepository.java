package camandedit.server.workspace.domain.repository;

import camandedit.server.workspace.domain.WorkSpace;
import camandedit.server.workspace.domain.WorkSpaceMember;
import java.util.List;

public interface WorkSpaceRepository {

  WorkSpace findById(Long workSpaceId);
  WorkSpace saveWorkSpace(WorkSpace workSpace);
  List<WorkSpace> findAllByUserId(Long userId);

  void saveWorkSpaceMember(WorkSpaceMember workSpaceMember);
}
