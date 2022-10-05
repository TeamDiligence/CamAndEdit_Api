package camandedit.server.user.exception;

import camandedit.server.global.exception.BusinessException;
import camandedit.server.global.exception.ErrorType;

public class NotMatchPasswordException extends BusinessException {

  public NotMatchPasswordException(String message) {
    super(message, ErrorType.NOT_MATCH_PASSWORD);
  }
}
