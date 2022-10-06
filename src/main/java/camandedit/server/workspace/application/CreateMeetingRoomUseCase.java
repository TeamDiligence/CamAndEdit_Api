package camandedit.server.workspace.application;


import camandedit.server.workspace.application.command.CreateMeetingRoomCommand;
import camandedit.server.workspace.domain.MeetingRoom;
import camandedit.server.workspace.domain.WorkSpace;
import camandedit.server.workspace.domain.repository.WorkSpaceRepository;
import camandedit.server.workspace.domain.service.WorkSpaceMemberChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateMeetingRoomUseCase {

  private final WorkSpaceRepository workSpaceRepository;
  private final WorkSpaceMemberChecker workSpaceMemberChecker;
  @Transactional
  public void create(CreateMeetingRoomCommand command){
    WorkSpace workSpace = workSpaceRepository.findById(command.getWorkSpaceId());
    workSpaceMemberChecker.checkParticiPateMember(command.getUserId(), workSpace.getId());
    workSpace.addMeetingRoom(new MeetingRoom(command.getName(), workSpace));
  }


}
