package camandedit.server.workspace.exception;

import camandedit.server.global.exception.BusinessException;
import camandedit.server.global.exception.ErrorType;

public class AlreadyInviteEmailException extends BusinessException {

  public AlreadyInviteEmailException(String message) {
    super(message, ErrorType.ALREADY_INVITE_EMAIL);
  }
}
