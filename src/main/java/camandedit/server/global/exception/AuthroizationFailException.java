package camandedit.server.global.exception;

public class AuthroizationFailException extends BusinessException{

  public AuthroizationFailException(String message) {
    super(message, ErrorType.AUTHORIZATION_FAIL);
  }
}
