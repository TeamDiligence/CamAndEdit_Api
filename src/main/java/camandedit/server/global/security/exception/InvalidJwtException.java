package camandedit.server.global.security.exception;

import camandedit.server.global.exception.BusinessException;
import camandedit.server.global.exception.ErrorType;

public class InvalidJwtException extends BusinessException {

  public InvalidJwtException(String message) {
    super(message, ErrorType.INVALID_INPUT);
  }
}
