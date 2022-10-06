package camandedit.server.workspace.application;

import camandedit.server.workspace.application.command.CheckInviteWorkSpaceCommand;
import camandedit.server.workspace.application.command.CreateMeetingRoomCommand;
import camandedit.server.workspace.application.command.CreateWorkSpaceCommand;
import camandedit.server.workspace.application.command.InviteWorkSpaceCommand;
import camandedit.server.workspace.application.dto.WorkSpaceMemberListResponse;
import camandedit.server.workspace.application.dto.WorkSpaceResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkSpaceFacade {

  private final CreateWorkSpaceUseCase createWorkSpaceUseCase;
  private final GetWorkSpaceUseCase getWorkSpaceUseCase;
  private final CreateMeetingRoomUseCase createMeetingRoomUseCase;
  private final InviteWorkSpaceUseCase inviteWorkSpaceUseCase;
  public void create(CreateWorkSpaceCommand command){
    createWorkSpaceUseCase.createWorkSpace(command);
  }

  public List<WorkSpaceResponse> getMyWorkSpaceList(Long userId){
    return getWorkSpaceUseCase.getMyWorkSpaceList(userId);
  }

  public WorkSpaceResponse getDetailInfo(Long workSpaceId){
    return getWorkSpaceUseCase.getDetail(workSpaceId);
  }

  public void creatMeetingRoom(CreateMeetingRoomCommand command){
    createMeetingRoomUseCase.create(command);
  }

  public WorkSpaceMemberListResponse getMemberList(Long workSpaceId, Long userId){
    return getWorkSpaceUseCase.getMemberList(workSpaceId, userId);
  }
  public void invite(InviteWorkSpaceCommand command){
    inviteWorkSpaceUseCase.invite(command);
  }

  public void checkInvite(CheckInviteWorkSpaceCommand command){
    inviteWorkSpaceUseCase.checkInvite(command);
  }
}
