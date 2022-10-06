package camandedit.server.workspace.application;

import camandedit.server.workspace.application.command.CreateMeetingRoomCommand;
import camandedit.server.workspace.application.command.CreateWorkSpaceCommand;
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
}
