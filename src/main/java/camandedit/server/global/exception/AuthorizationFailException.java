package camandedit.server.global.exception;

public class AuthorizationFailException extends BusinessException{

  public AuthorizationFailException(String message) {
    super(message, ErrorType.AUTHORIZATION_FAIL);
  }
}
