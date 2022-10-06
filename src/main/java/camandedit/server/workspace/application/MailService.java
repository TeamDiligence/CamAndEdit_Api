package camandedit.server.workspace.application;

public interface MailService {

  void sendMail(String email, Long workSpaceId);
}
