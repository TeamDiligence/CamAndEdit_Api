package camandedit.server.workspace.domain.repository;

import camandedit.server.workspace.domain.WorkSpace;
import camandedit.server.workspace.domain.WorkSpaceMember;
import java.util.List;

public interface WorkSpaceRepository {

  WorkSpace findById(Long workSpaceId);
  void saveWorkSpace(WorkSpace workSpace);
  List<WorkSpace> findAllByUserId(Long userId);

  WorkSpace findByIdWithMeetingRoom(Long workSpaceId);
  void saveWorkSpaceMember(WorkSpaceMember workSpaceMember);

  List<WorkSpaceMember> findWorkSpaceMembersByWorkSpaceId(Long userId, Long workspaceId);
  WorkSpace findByIdWithMember(Long workSpaceId);
}
