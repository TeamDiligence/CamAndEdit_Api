package camandedit.server.workspace.exception;

import camandedit.server.global.exception.BusinessException;
import camandedit.server.global.exception.ErrorType;

public class AlreadtPariticipateException extends BusinessException {

  public AlreadtPariticipateException(String message) {
    super(message, ErrorType.ALREADY_PARTICIPATE_WORKSPACE);
  }
}
