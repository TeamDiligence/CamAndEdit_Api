package camandedit.server.workspace.application;

import camandedit.server.workspace.application.command.CreateWorkSpaceCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkSpaceFacade {

  private final CreateWorkSpaceUseCase createWorkSpaceUseCase;

  public void create(CreateWorkSpaceCommand command){
    createWorkSpaceUseCase.createWorkSpace(command);
  }
}
