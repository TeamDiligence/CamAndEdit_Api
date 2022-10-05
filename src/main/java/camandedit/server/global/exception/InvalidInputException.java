package camandedit.server.global.exception;

public class InvalidInputException extends BusinessException{

  public InvalidInputException(String message) {
    super(message, ErrorType.INVALID_INPUT);
  }
}
