package camandedit.server.workspace.domain.service;

import camandedit.server.global.exception.AuthorizationFailException;
import camandedit.server.workspace.domain.WorkSpaceMember;
import camandedit.server.workspace.domain.repository.WorkSpaceRepository;
import camandedit.server.workspace.exception.AlreadtPariticipateException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkSpaceMemberChecker {

  private final WorkSpaceRepository workSpaceRepository;

  public boolean isParticipateMember(Long userId, Long workSpaceId) {
    List<WorkSpaceMember> memberList = workSpaceRepository.findWorkSpaceMembersByWorkSpaceId(
        userId, workSpaceId);

    Optional<WorkSpaceMember> isFind = memberList.stream()
        .filter((member) -> member.getWorkSpaceMemberKey().getUserId().equals(userId))
        .findAny();

    return isFind.isPresent();
  }

  public void checkParticipateMember(Long userId, Long workSpaceId) {
    if (!isParticipateMember(userId, workSpaceId)) {
      throw new AuthorizationFailException("해당 워크스페이스에 참여중이 아닙니다.");
    }
  }

  public void checkNotParticipateMember(Long userId, Long workSpaceId) {
    if (isParticipateMember(userId, workSpaceId)) {
      throw new AlreadtPariticipateException("이미 워크스페이스에 참여중입니다.");
    }
  }
}
