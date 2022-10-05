package camandedit.server.global.exception;

import lombok.Getter;

@Getter
public enum ErrorType {
  INVALID_INPUT(400),
  EXPIRED_JWT(400),
  AUTHENTICATION_FAIL(401),
  AUTHORIZATION_FAIL(403),
  NOT_FOUND_RESOURCE(404),
  INTERNAL_SERVER_ERROR(500);

  private final int statusCode;

  ErrorType(int statusCode) {
    this.statusCode = statusCode;
  }
}
