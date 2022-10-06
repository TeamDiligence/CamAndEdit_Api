package camandedit.server.workspace.infra;

import camandedit.server.global.exception.NotFoundResourceException;
import camandedit.server.user.domain.User;
import camandedit.server.workspace.domain.WorkSpace;
import camandedit.server.workspace.domain.WorkSpaceMember;
import camandedit.server.workspace.domain.repository.WorkSpaceRepository;
import camandedit.server.workspace.infra.jpa.JpaWorkSpaceMemberRepository;
import camandedit.server.workspace.infra.jpa.JpaWorkSpaceRepository;
import camandedit.server.workspace.infra.query.WorkSpaceQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkSpaceRepositoryImpl implements WorkSpaceRepository {

  private final JpaWorkSpaceRepository jpaWorkSpaceRepository;
  private final JpaWorkSpaceMemberRepository jpaWorkSpaceMemberRepository;
  private final WorkSpaceQueryRepository workSpaceQueryRepository;

  @Override
  public WorkSpace findById(Long workSpaceId) {
    return jpaWorkSpaceRepository.findById(workSpaceId)
        .orElseThrow(() -> new NotFoundResourceException("해당 워크스페이스를 찾을 수 없습니다."));
  }

  @Override
  public WorkSpace saveWorkSpace(WorkSpace workSpace) {
    return jpaWorkSpaceRepository.save(workSpace);
  }

  @Override
  public WorkSpace findByIdWithMeetingRoom(Long workSpaceId) {
    WorkSpace workSpace = workSpaceQueryRepository.findByIdWithMeetingRoom(workSpaceId);
    System.out.println(workSpaceId + " " + workSpace);
    if (workSpace == null) {
      throw new NotFoundResourceException("해당 워크스페이스를 찾을 수 없습니다.");
    }
    return workSpace;
  }

  @Override
  public WorkSpace findByIdWithMember(Long workSpaceId) {
    WorkSpace workSpace = workSpaceQueryRepository.findByIdWithMember(workSpaceId);
    if(workSpace == null){
      throw new NotFoundResourceException("해당 워크스페이스를 찾을 수 없습니다.");
    }
    return workSpace;
  }

  @Override
  public List<WorkSpaceMember> findWorkSpaceMembersByWorkSpaceId(Long userId, Long workSpaceId) {
    return workSpaceQueryRepository.findMemberByWorkSpaceId(userId, workSpaceId);
  }

  @Override
  public void saveWorkSpaceMember(WorkSpaceMember workSpaceMember) {
    jpaWorkSpaceMemberRepository.save(workSpaceMember);
  }

  @Override
  public List<WorkSpace> findAllByUserId(Long userId) {
    return workSpaceQueryRepository.findWorkSpaceListByUserId(userId);
  }
}
