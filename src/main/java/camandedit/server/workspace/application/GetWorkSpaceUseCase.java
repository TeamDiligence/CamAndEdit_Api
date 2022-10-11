package camandedit.server.workspace.application;


import camandedit.server.user.domain.User;
import camandedit.server.user.domain.repository.UserRepository;
import camandedit.server.workspace.application.dto.MemberProfileResponse;
import camandedit.server.workspace.application.dto.WorkSpaceMemberListResponse;
import camandedit.server.workspace.application.dto.WorkSpaceResponse;
import camandedit.server.workspace.domain.InviteMember;
import camandedit.server.workspace.domain.WorkSpace;
import camandedit.server.workspace.domain.WorkSpaceMember;
import camandedit.server.workspace.domain.repository.InviteMemberRepository;
import camandedit.server.workspace.domain.repository.WorkSpaceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetWorkSpaceUseCase {

  private final WorkSpaceRepository workSpaceRepository;
  private final UserRepository userRepository;
  private final InviteMemberRepository inviteMemberRepository;

  public List<WorkSpaceResponse> getMyWorkSpaceList(Long userId) {
    User user = userRepository.findById(userId);
    List<WorkSpace> workSpaceList = workSpaceRepository.findAllByUserId(user.getId());
    return workSpaceList.stream().map(WorkSpaceResponse::from).toList();
  }

  public WorkSpaceResponse getDetail(Long workSpaceId) {
    WorkSpace workSpace = workSpaceRepository.findByIdWithMeetingRoom(workSpaceId);
    return WorkSpaceResponse.toDetail(workSpace);
  }

  public WorkSpaceMemberListResponse getMemberList(Long workSpaceId, Long userId) {
    WorkSpace workSpace = workSpaceRepository.findByIdWithMember(workSpaceId);
    List<InviteMember> inviteMemberList = inviteMemberRepository.findNotApproveMemberList(
        workSpaceId);

    return WorkSpaceMemberListResponse.from(workSpace.getWorkSpaceMembers(), inviteMemberList);
  }

  public MemberProfileResponse getProfile(Long workSpaceId, Long userId) {
    WorkSpaceMember member = workSpaceRepository.findMember(workSpaceId, userId);
    return MemberProfileResponse.from(member);
  }
}
