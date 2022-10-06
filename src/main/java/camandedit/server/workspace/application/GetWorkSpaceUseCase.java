package camandedit.server.workspace.application;


import camandedit.server.user.domain.User;
import camandedit.server.user.domain.repository.UserRepository;
import camandedit.server.workspace.application.dto.WorkSpaceResponse;
import camandedit.server.workspace.domain.WorkSpace;
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

  public List<WorkSpaceResponse> getMyWorkSpaceList(Long userId) {
    User user = userRepository.findById(userId);
    List<WorkSpace> workSpaceList = workSpaceRepository.findAllByUserId(user.getId());
    return workSpaceList.stream().map(WorkSpaceResponse::from).toList();
  }

  public WorkSpaceResponse getDetail(Long workSpaceId){
    WorkSpace workSpace = workSpaceRepository.findByIdWithMeetingRoom(workSpaceId);
    return WorkSpaceResponse.toDetail(workSpace);
  }
}
