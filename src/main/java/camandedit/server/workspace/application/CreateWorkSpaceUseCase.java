package camandedit.server.workspace.application;

import camandedit.server.global.common.S3Uploader;
import camandedit.server.user.domain.User;
import camandedit.server.user.domain.repository.UserRepository;
import camandedit.server.workspace.application.command.CreateWorkSpaceCommand;
import camandedit.server.workspace.domain.WorkSpace;
import camandedit.server.workspace.domain.WorkSpaceMember;
import camandedit.server.workspace.domain.repository.WorkSpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateWorkSpaceUseCase {

  private final S3Uploader s3Uploader;
  private final UserRepository userRepository;
  private final WorkSpaceRepository workSpaceRepository;

  @Transactional
  public void createWorkSpace(CreateWorkSpaceCommand command) {
    User user = userRepository.findById(command.getUserId());
    String imageUrl = s3Uploader.uploadFile("camandedit/", command.getFile());
    WorkSpace workSpace = WorkSpace.initWorkSpace(command.getName(), imageUrl, user.getId());
    workSpaceRepository.saveWorkSpace(workSpace);
    workSpace.addMember(WorkSpaceMember.createAdmin(user, workSpace));
  }
}
