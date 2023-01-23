package camandedit.server.user.exception;

import camandedit.server.global.exception.BusinessException;
import camandedit.server.global.exception.ErrorType;

public class InvalidJwtException extends BusinessException {

  public InvalidJwtException(String message) {
    super(message, ErrorType.AUTHENTICATION_FAIL);
  }
}
