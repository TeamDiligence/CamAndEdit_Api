package camandedit.server.workspace.application;

import camandedit.server.user.domain.User;
import camandedit.server.user.domain.repository.UserRepository;
import camandedit.server.workspace.application.command.CheckInviteWorkSpaceCommand;
import camandedit.server.workspace.application.command.InviteWorkSpaceCommand;
import camandedit.server.workspace.domain.InviteMember;
import camandedit.server.workspace.domain.WorkSpace;
import camandedit.server.workspace.domain.WorkSpaceMember;
import camandedit.server.workspace.domain.repository.InviteMemberRepository;
import camandedit.server.workspace.domain.repository.WorkSpaceRepository;
import camandedit.server.workspace.domain.service.InviteMemberChecker;
import camandedit.server.workspace.domain.service.WorkSpaceMemberChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InviteWorkSpaceUseCase {

  private final MailService mailService;
  private final WorkSpaceRepository workSpaceRepository;
  private final InviteMemberRepository inviteMemberRepository;
  private final WorkSpaceMemberChecker workSpaceMemberChecker;
  private final InviteMemberChecker inviteMemberChecker;
  private final UserRepository userRepository;

  @Transactional
  public void invite(InviteWorkSpaceCommand command) {
    WorkSpace workSpace = workSpaceRepository.findById(command.getWorkSpaceId());
    workSpace.checkAdmin(command.getAdminId());
    inviteMemberChecker.checkAlreadyInvite(command.getEmail(), workSpace.getId());
    mailService.sendMail(command.getEmail(), workSpace.getId());
    inviteMemberRepository.save(InviteMember.inviteInit(command.getEmail(), workSpace.getId()));
  }


  @Transactional
  public void checkInvite(CheckInviteWorkSpaceCommand command) {
    WorkSpace workSpace = workSpaceRepository.findById(command.getWorkSpaceId());
    workSpaceMemberChecker.checkNotParticipateMember(command.getUserId(), workSpace.getId());
    InviteMember inviteMember = inviteMemberChecker.checkExistInvite(command.getEmail(),
        workSpace.getId());

    inviteMember.invite();
    User user = userRepository.findById(command.getUserId());
    workSpace.addMember(WorkSpaceMember.createMember(user, workSpace));
  }
}
