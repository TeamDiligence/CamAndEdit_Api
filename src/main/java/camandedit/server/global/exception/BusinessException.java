package camandedit.server.global.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

  private final ErrorType errorType;

  public BusinessException(ErrorType errorType) {
    this.errorType = errorType;
  }

  public BusinessException(String message, ErrorType errorType) {
    super(message);
    this.errorType = errorType;
  }
}
