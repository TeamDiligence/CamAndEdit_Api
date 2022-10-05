package camandedit.server.workspace.infra;

import camandedit.server.global.exception.NotFoundResourceException;
import camandedit.server.workspace.domain.WorkSpace;
import camandedit.server.workspace.domain.WorkSpaceMember;
import camandedit.server.workspace.domain.repository.WorkSpaceRepository;
import camandedit.server.workspace.infra.jpa.JpaWorkSpaceMemberRepository;
import camandedit.server.workspace.infra.jpa.JpaWorkSpaceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkSpaceRepositoryImpl implements WorkSpaceRepository {

  private final JpaWorkSpaceRepository jpaWorkSpaceRepository;
  private final JpaWorkSpaceMemberRepository jpaWorkSpaceMemberRepository;

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
  public void saveWorkSpaceMember(WorkSpaceMember workSpaceMember) {
    jpaWorkSpaceMemberRepository.save(workSpaceMember);
  }

  @Override
  public List<WorkSpace> findAllByUserId(Long userId) {
    return null;
  }
}
