package camandedit.server.user.exception;

import camandedit.server.global.exception.BusinessException;
import camandedit.server.global.exception.ErrorType;

public class ExpiredJwtFailException extends BusinessException {

  public ExpiredJwtFailException(String message) {
    super(message, ErrorType.EXPIRED_JWT);
  }
}
