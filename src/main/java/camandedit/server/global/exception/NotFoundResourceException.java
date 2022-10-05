package camandedit.server.global.exception;

public class NotFoundResourceException extends BusinessException{

  public NotFoundResourceException(String message) {
    super(message, ErrorType.NOT_FOUND_RESOURCE);
  }
}
