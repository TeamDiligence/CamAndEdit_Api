package camandedit.server.workspace.application;

import camandedit.server.workspace.application.command.UpdateProfileCommand;
import camandedit.server.workspace.domain.WorkSpaceMember;
import camandedit.server.workspace.domain.repository.WorkSpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UpdateProfileUseCase {

  private final WorkSpaceRepository workSpaceRepository;

  @Transactional
  public void updateProfile(UpdateProfileCommand command) {
    WorkSpaceMember member = workSpaceRepository.findMember(command.getWorkSpaceId(),
        command.getUserId());
    member.updateProfile(command.getNickname(), command.getDescription());
  }
}
